package com.sparta.scheduleapp.schedule.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CreateRequestDto {
    private List<Long> userIds;
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
