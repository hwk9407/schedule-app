package com.sparta.scheduleapp.user.dto.response;

import com.sparta.scheduleapp.entity.Gender;
import com.sparta.scheduleapp.entity.User;
import lombok.Getter;

@Getter
public class UserDto {
    private Long userId;
    private String username;
    private String email;
    private Gender gender;

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.username = user.getUserName();
        this.email = user.getEmail();
        this.gender = user.getGender();
    }
}
