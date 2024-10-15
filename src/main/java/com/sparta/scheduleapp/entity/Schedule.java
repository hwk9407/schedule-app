package com.sparta.scheduleapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedule")
public class Schedule extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    public Schedule(String userName, String title, String content, LocalDateTime startDate, LocalDateTime endDate) {
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;

        checkValidDate();
    }

    private void checkValidDate() {

        if (startDate == null) startDate = LocalDateTime.now();
        if (endDate == null) endDate = startDate;
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("종료일은 반드시 시작일보다 이후 날짜여야 합니다.");
        }

    }
}
