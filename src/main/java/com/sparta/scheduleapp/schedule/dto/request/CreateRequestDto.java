package com.sparta.scheduleapp.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CreateRequestDto {
    @NotEmpty
    private List<Long> userIds;

    @NotNull
    @Size(max = 50)
    private String title;

    @NotNull
    @Size(max = 255)
    private String content;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
