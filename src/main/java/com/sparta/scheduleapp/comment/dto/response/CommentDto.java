package com.sparta.scheduleapp.comment.dto.response;

import com.sparta.scheduleapp.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentDto {
    private Long commentId;
    private String userName;
    private String content;
    private Long scheduleId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public CommentDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.userName = comment.getUserName();
        this.content = comment.getContent();
        this.scheduleId = comment.getSchedule().getScheduleId();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
    }
}
