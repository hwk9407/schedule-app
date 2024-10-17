package com.sparta.scheduleapp.common.dto;


import lombok.Getter;

@Getter
public class ResponseDto {
    String message;

    public ResponseDto(String message) {
        this.message = message;
    }
}
