package com.sparta.scheduleapp.schedule.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleRequestDto {
    private String userName;
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
