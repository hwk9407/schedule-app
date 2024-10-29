package com.sparta.scheduleapp.schedule.dto.response;

import com.sparta.scheduleapp.common.dto.ResponseDto;

import lombok.Getter;

@Getter
public class DeleteResponseDto extends ResponseDto {
    private Long deletedId;

    public DeleteResponseDto(String message, Long scheduleId) {
        super(message);
        this.deletedId = scheduleId;
    }
}
