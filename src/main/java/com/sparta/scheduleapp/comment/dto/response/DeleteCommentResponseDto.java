package com.sparta.scheduleapp.comment.dto.response;

import com.sparta.scheduleapp.common.dto.ResponseDto;

public class DeleteCommentResponseDto extends ResponseDto {
    private final Long deletedId;

    public DeleteCommentResponseDto(String message, Long commentId) {
        super(message);
        this.deletedId = commentId;
    }
}
