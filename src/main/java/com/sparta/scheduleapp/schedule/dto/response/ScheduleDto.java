package com.sparta.scheduleapp.schedule.dto.response;

import com.sparta.scheduleapp.entity.Schedule;
import com.sparta.scheduleapp.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleDto {
    private Long scheduleId;
    private List<Long> userIds;
    private String title;
    private String content;
    private int commentCount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ScheduleDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.userIds = schedule.getUsers().stream()
                .map(User::getUserId)
                .toList();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.commentCount = schedule.getCommentsCount();
        this.startDate = schedule.getStartDate();
        this.endDate = schedule.getEndDate();
        this.createdDate = schedule.getCreatedDate();
        this.modifiedDate = schedule.getModifiedDate();
    }
}
