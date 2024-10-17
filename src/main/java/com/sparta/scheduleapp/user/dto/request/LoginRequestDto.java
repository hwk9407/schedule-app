package com.sparta.scheduleapp.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "유효한 이메일 주소를 입력하세요.")
    private String email;

    @NotBlank
    @Size(min = 8, max = 16)
    private String password;
}
/*
- email : String(필수)
  - 설명 : 이메일 형식
- password : String(필수)
  - 설명 : 8자 이상 16자 이하, 특수 문자 허용
 */