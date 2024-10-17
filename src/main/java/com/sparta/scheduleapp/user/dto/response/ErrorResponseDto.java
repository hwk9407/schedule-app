package com.sparta.scheduleapp.user.dto.response;

import com.sparta.scheduleapp.common.dto.ResponseDto;

public class ErrorResponseDto extends ResponseDto {

    public ErrorResponseDto(String message) {
        super(message);
    }
}
