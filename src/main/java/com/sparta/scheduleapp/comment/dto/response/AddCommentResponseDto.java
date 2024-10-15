package com.sparta.scheduleapp.comment.dto.response;

import com.sparta.scheduleapp.entity.Comment;

public class AddCommentResponseDto extends ResponseDto {
    private Long commentId;

    public AddCommentResponseDto(String message, Long commentId) {
        super(message);
        this.commentId = commentId;
    }
}
