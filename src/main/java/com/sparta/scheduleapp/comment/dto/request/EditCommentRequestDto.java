package com.sparta.scheduleapp.comment.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class EditCommentRequestDto {
    @NotNull
    @Size(max = 50)
    private String content;
}
