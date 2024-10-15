package com.sparta.scheduleapp.schedule.dto.response;

import com.sparta.scheduleapp.entity.Schedule;
import lombok.Getter;

@Getter
public class RetrieveResponseDto extends ResponseDto {

    private ScheduleDto schedule;

    public RetrieveResponseDto(String message, Schedule schedule) {
        super(message);
        this.schedule = new ScheduleDto(schedule);
    }
}
