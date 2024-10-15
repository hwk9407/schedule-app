package com.sparta.scheduleapp.comment.dto.response;

import com.sparta.scheduleapp.entity.Comment;

public class EditCommentResponseDto extends ResponseDto {
    CommentDto comment;
    public EditCommentResponseDto(String message, Comment comment) {
        super(message);
        this.comment = new CommentDto(comment);
    }
}
