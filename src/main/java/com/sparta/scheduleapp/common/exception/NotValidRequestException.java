package com.sparta.scheduleapp.common.exception;

import lombok.Getter;

@Getter
public class NotValidRequestException extends RuntimeException {
    private final String errorCode;

    public NotValidRequestException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
