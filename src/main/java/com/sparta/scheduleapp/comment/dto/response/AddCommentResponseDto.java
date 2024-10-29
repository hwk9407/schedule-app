package com.sparta.scheduleapp.comment.dto.response;

import com.sparta.scheduleapp.common.dto.ResponseDto;

public class AddCommentResponseDto extends ResponseDto {
    private Long commentId;

    public AddCommentResponseDto(String message, Long commentId) {
        super(message);
        this.commentId = commentId;
    }
}
