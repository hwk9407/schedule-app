package com.sparta.scheduleapp.schedule.dto.response;


import lombok.Getter;

@Getter
public abstract class ResponseDto {
    String message;

    // protected 접근 제어자로 서브클래스에서만 접근 가능
    protected ResponseDto(String message) {
        this.message = message;
    }
}
