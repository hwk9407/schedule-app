package com.sparta.scheduleapp.common.exception;

import lombok.Getter;

@Getter
public class ClientBadRequestException extends RuntimeException {

    private final String errorCode;
    public ClientBadRequestException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
