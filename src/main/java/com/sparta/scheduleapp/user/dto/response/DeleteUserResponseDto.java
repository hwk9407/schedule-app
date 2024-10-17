package com.sparta.scheduleapp.user.dto.response;

import com.sparta.scheduleapp.common.dto.ResponseDto;
import lombok.Getter;

@Getter
public class DeleteUserResponseDto extends ResponseDto {
    private Long deletedId;
    public DeleteUserResponseDto(String message, Long userId) {
        super(message);
        this.deletedId = userId;
    }
}
