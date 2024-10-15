package com.sparta.scheduleapp.schedule.repository;

import com.sparta.scheduleapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

//    Optional<Schedule> findByUserName(String userName);

    Optional<Schedule> findByScheduleId(Long scheduleId);
}
