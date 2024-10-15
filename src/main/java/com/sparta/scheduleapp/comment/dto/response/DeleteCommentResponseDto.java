package com.sparta.scheduleapp.comment.dto.response;

public class DeleteCommentResponseDto extends ResponseDto {
    private Long deletedId;

    public DeleteCommentResponseDto(String message, Long commentId) {
        super(message);
        this.deletedId = commentId;
    }
}
