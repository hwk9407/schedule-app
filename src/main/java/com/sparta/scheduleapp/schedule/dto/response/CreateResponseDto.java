package com.sparta.scheduleapp.schedule.dto.response;

import com.sparta.scheduleapp.common.dto.ResponseDto;
import lombok.Getter;

@Getter
public class CreateResponseDto extends ResponseDto {

    private final Long scheduleId;

    public CreateResponseDto(String message, Long scheduleId) {
        super(message);
        this.scheduleId = scheduleId;
    }

}
