package com.sparta.scheduleapp.schedule.repository;

import com.sparta.scheduleapp.entity.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScheduleRepository extends JpaRepository<UserSchedule, Long> {
}
