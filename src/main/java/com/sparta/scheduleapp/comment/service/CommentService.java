package com.sparta.scheduleapp.comment.service;

import com.sparta.scheduleapp.comment.dto.request.AddCommentRequestDto;
import com.sparta.scheduleapp.comment.dto.request.EditCommentRequestDto;

import com.sparta.scheduleapp.comment.dto.response.AddCommentResponseDto;
import com.sparta.scheduleapp.comment.dto.response.DeleteCommentResponseDto;
import com.sparta.scheduleapp.comment.dto.response.EditCommentResponseDto;
import com.sparta.scheduleapp.comment.dto.response.RetrieveCommentsResponseDto;
import com.sparta.scheduleapp.common.dto.ResponseDto;
import com.sparta.scheduleapp.comment.repository.CommentRepository;
import com.sparta.scheduleapp.entity.Comment;
import com.sparta.scheduleapp.entity.Schedule;
import com.sparta.scheduleapp.entity.User;
import com.sparta.scheduleapp.schedule.repository.ScheduleRepository;
import com.sparta.scheduleapp.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseDto addComment(Long jwtUserId, Long scheduleId, AddCommentRequestDto reqDto) {
        User user = userRepository.findById(jwtUserId).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 유저입니다.")
        );
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 일정입니다.")
        );
        Comment comment = new Comment(
                schedule,
                user,
                reqDto.getContent()
        );
        commentRepository.save(comment);

        return new AddCommentResponseDto("댓글을 성공적으로 등록하였습니다.", comment.getCommentId());
    }

    public ResponseDto retrieveComment(Long scheduleId) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 일정입니다.")
        );
        List<Comment> comments = schedule.getComments();

        return new RetrieveCommentsResponseDto("댓글을 성공적으로 조회하였습니다.", comments);
    }

    public ResponseDto editComment(Long jwtUserId, Long scheduleId, Long commentId, EditCommentRequestDto reqDto) {
        User user = userRepository.findById(jwtUserId).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 유저입니다.")
        );
        scheduleRepository.findByScheduleId(scheduleId).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 일정입니다.")
        );
        Comment comment = commentRepository.getByCommentId(commentId).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 댓글입니다.")
        );

        if (!comment.getUser().getUserId().equals(user.getUserId())) {
            throw new IllegalArgumentException("본인이 쓴 댓글만 수정할 수 있습니다.");
        }

        comment.setContent(reqDto.getContent());
        commentRepository.save(comment); // 생략 가능하나, 생략하면 Auditing 기능이 동작 안함

        return new EditCommentResponseDto("댓글이 성공적으로 수정되었습니다.", comment);

    }

    public ResponseDto deleteComment(Long jwtUserId, Long scheduleId, Long commentId) {
        User user = userRepository.findById(jwtUserId).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 유저입니다.")
        );
        scheduleRepository.findByScheduleId(scheduleId).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 일정입니다.")
        );
        Comment comment = commentRepository.getByCommentId(commentId).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 댓글입니다.")
        );
        if (!comment.getUser().getUserId().equals(user.getUserId())) {
            throw new IllegalArgumentException("본인이 쓴 댓글만 삭제할 수 있습니다.");
        }

        commentRepository.deleteById(commentId);

        return new DeleteCommentResponseDto("댓글이 성공적으로 삭제되었습니다.", commentId);
    }
}
