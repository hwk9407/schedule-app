package com.sparta.scheduleapp.comment.service;

import com.sparta.scheduleapp.comment.dto.request.AddCommentRequestDto;
import com.sparta.scheduleapp.comment.dto.request.EditCommentRequestDto;
import com.sparta.scheduleapp.comment.dto.response.*;
import com.sparta.scheduleapp.comment.repository.CommentRepository;
import com.sparta.scheduleapp.entity.Comment;
import com.sparta.scheduleapp.entity.Schedule;
import com.sparta.scheduleapp.schedule.repository.ScheduleRepository;
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

    @Transactional
    public ResponseDto addComment(Long scheduleId, AddCommentRequestDto reqDto) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 일정입니다."));
        Comment comment = new Comment(
                schedule,
                reqDto.getUserName(),
                reqDto.getContent()
                );
        commentRepository.save(comment);

        return new AddCommentResponseDto("댓글을 성공적으로 등록하였습니다.", comment.getCommentId());
    }

    public ResponseDto retrieveComment(Long scheduleId) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 일정입니다."));
        List<Comment> comments = schedule.getComments();

        return new RetrieveCommentsResponseDto("댓글을 성공적으로 조회하였습니다.", comments);
    }

    public ResponseDto editComment(Long scheduleId, Long commentId, EditCommentRequestDto reqDto) {
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 일정입니다."));
        Comment comment = commentRepository.getByCommentId(commentId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 댓글입니다."));

        comment.setContent(reqDto.getContent());
        commentRepository.save(comment); // 생략 가능하나, 생략하면 Auditing 기능이 동작 안함

        return new EditCommentResponseDto("댓글이 성공적으로 수정되었습니다.", comment);

    }

    public ResponseDto deleteComment(Long scheduleId, Long commentId) {
        // Schedule schedule = scheduleRepository.findByScheduleId(scheduleId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 일정입니다."));
        Comment comment = commentRepository.getByCommentId(commentId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 댓글입니다."));

        commentRepository.deleteById(commentId);

        return new DeleteCommentResponseDto("댓글이 성공적으로 삭제되었습니다.", commentId);
    }
}
