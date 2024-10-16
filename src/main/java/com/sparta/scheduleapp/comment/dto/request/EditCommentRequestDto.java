package com.sparta.scheduleapp.comment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EditCommentRequestDto {
    @NotNull
    private String content;
}
