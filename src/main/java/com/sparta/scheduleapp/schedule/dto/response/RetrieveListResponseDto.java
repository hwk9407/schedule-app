package com.sparta.scheduleapp.schedule.dto.response;

import com.sparta.scheduleapp.entity.Schedule;
import lombok.Getter;

import java.util.List;

@Getter
public class RetrieveListResponseDto extends ResponseDto {

    private List<ScheduleDto> schedules;

    public RetrieveListResponseDto(String message, List<Schedule> schedules) {
        super(message);
        this.schedules = schedules.stream()
                .map(ScheduleDto::new)
                .toList();
    }
}
