package com.sparta.scheduleapp.comment.dto.request;

import lombok.Getter;

@Getter
public class AddCommentRequestDto {
    private Long userId;
    private String content;
}
