package com.sparta.scheduleapp.user.dto.response;

import com.sparta.scheduleapp.common.dto.ResponseDto;
import com.sparta.scheduleapp.entity.User;
import lombok.Getter;

import java.util.List;

@Getter
public class RetrieveUserLIstResponseDto extends ResponseDto {
    private List<UserDto> users;

    public RetrieveUserLIstResponseDto(String message, List<User> users) {
        super(message);
        this.users = users.stream()
                .map(UserDto::new)
                .toList();
    }
}
