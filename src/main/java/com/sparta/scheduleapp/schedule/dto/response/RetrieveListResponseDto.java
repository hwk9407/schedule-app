package com.sparta.scheduleapp.schedule.dto.response;

import com.sparta.scheduleapp.common.pagination.Pagination;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class RetrieveListResponseDto extends ResponseDto {

    private List<ScheduleDto> schedules;
    private Pagination pagination;

    public RetrieveListResponseDto(String message, Page<ScheduleDto> schedules) {
        super(message);
        this.schedules = schedules.getContent();
        this.pagination = new Pagination(schedules);
//        this.schedules = schedules.stream()
//                .map(ScheduleDto::new)
//                .toList();
    }
}
