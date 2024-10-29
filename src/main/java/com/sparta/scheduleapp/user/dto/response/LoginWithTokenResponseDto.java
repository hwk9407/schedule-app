package com.sparta.scheduleapp.user.dto.response;
import com.sparta.scheduleapp.common.dto.ResponseDto;
import lombok.Getter;

@Getter
public class LoginWithTokenResponseDto extends ResponseDto {

    private final String token;

    public LoginWithTokenResponseDto(String message, String token) {
        super(message);
        this.token = token;
    }
}
