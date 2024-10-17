package com.sparta.scheduleapp.user.dto.response;

import com.sparta.scheduleapp.common.dto.ResponseDto;
import com.sparta.scheduleapp.entity.User;
import lombok.Getter;

@Getter
public class EditUserResponseDto extends ResponseDto {
    private UserDto user;

    public EditUserResponseDto(String message, User user) {
        super(message);
        this.user = new UserDto(user);
    }
}
