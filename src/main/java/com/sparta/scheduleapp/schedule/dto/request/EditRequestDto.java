package com.sparta.scheduleapp.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EditRequestDto {
    @NotNull
    @Size(max = 20)
    private String title;
    @NotNull
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
