package com.sparta.scheduleapp.user.dto.request;

import com.sparta.scheduleapp.entity.Gender;
import lombok.Getter;

@Getter
public class EditUserRequestDto {
    private String userName;
    private Gender gender;
    private String email;
}
