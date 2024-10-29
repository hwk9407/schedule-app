package com.sparta.scheduleapp.user.dto.response;

import com.sparta.scheduleapp.common.dto.ResponseDto;
import lombok.Getter;

@Getter
public class AddUserWithTokenResponseDto extends ResponseDto {

    private final Long userId;
    private final String token;
    public AddUserWithTokenResponseDto(String message, Long userId, String token) {
        super(message);
        this.userId = userId;
        this.token = token;
    }
}
