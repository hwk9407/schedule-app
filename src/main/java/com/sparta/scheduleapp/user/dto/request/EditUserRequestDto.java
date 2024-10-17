package com.sparta.scheduleapp.user.dto.request;

import com.sparta.scheduleapp.entity.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class EditUserRequestDto {
    @NotBlank
    @Size(max = 20)
    private String userName;

    @NotNull
    private Gender gender;

    @NotBlank
    @Size(min = 8, max = 16)
    private String password;
}
