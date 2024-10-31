package com.sparta.scheduleapp.common.exception;

import lombok.Getter;

@Getter
public class UserAccessDeniedException extends RuntimeException {
    private final String errorCode;
    public UserAccessDeniedException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
