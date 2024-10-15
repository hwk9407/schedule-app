package com.sparta.scheduleapp.schedule.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EditRequestDto {
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
