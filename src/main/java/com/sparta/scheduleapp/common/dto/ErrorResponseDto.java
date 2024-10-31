package com.sparta.scheduleapp.common.dto;

import lombok.Getter;

@Getter
public class ErrorResponseDto extends ResponseDto {
    private final String errorCode;

    public ErrorResponseDto(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
