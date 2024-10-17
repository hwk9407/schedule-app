package com.sparta.scheduleapp.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotBlank
    private String userName;

    @NotBlank
    @Size(min = 2, max = 16)
    private String password;
}
/*
- userName : String(필수)
  - 설명 : 대소문자, 숫자를 조합
- password : String(필수)
  - 설명 : 8자 이상 16자 이하, 특수 문자 허용
 */