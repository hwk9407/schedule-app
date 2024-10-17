package com.sparta.scheduleapp.comment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddCommentRequestDto {
    @NotNull
    private Long userId;
    @NotNull
    private String content;
}
