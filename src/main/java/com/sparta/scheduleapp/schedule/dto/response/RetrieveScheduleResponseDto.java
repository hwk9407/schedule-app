package com.sparta.scheduleapp.schedule.dto.response;

import com.sparta.scheduleapp.entity.Schedule;
import lombok.Getter;

@Getter
public class RetrieveScheduleResponseDto extends ResponseDto {

    private ScheduleDto schedule;

    public RetrieveScheduleResponseDto(String message, Schedule schedule) {
        super(message);
        this.schedule = new ScheduleDto(schedule);
    }
}
