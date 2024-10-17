package com.sparta.scheduleapp.schedule.dto.response;

import com.sparta.scheduleapp.entity.Schedule;
import lombok.Getter;

@Getter
public class EditResponseDto extends ResponseDto {
    ScheduleDto schedule;
    public EditResponseDto(String message, Schedule schedule) {
        super(message);
        this.schedule = new ScheduleDto(schedule);
    }
}
