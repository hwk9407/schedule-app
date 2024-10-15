package com.sparta.scheduleapp.schedule.dto.response;

import lombok.Getter;

@Getter
public class CreateScheduleResponseDto extends ResponseDto {

    private final Long scheduleId;

    public CreateScheduleResponseDto(String message, Long scheduleId) {
        super(message);
        this.scheduleId = scheduleId;
    }

}
