package com.sparta.scheduleapp.schedule.repository;

import com.sparta.scheduleapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
