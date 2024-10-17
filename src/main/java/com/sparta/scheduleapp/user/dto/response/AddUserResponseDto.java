package com.sparta.scheduleapp.user.dto.response;

import com.sparta.scheduleapp.common.dto.ResponseDto;
import lombok.Getter;

@Getter
public class AddUserResponseDto extends ResponseDto {

    private final Long userId;

    public AddUserResponseDto(String message, Long userId) {
        super(message);
        this.userId = userId;
    }
}
