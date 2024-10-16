package com.sparta.scheduleapp.user.dto.request;

import com.sparta.scheduleapp.entity.Gender;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CreateUserRequestDto {
    private String userName;
    // private String password;
    private Gender gender;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "유효한 이메일 주소를 입력하세요.")
    private String email;
}
