package com.sparta.scheduleapp.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CreateRequestDto {
    @NotBlank
    private List<Long> userIds;
    @NotNull
    @Size(max = 20)
    private String title;
    @NotNull
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
