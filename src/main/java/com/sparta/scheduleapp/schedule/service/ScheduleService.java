package com.sparta.scheduleapp.schedule.service;

import com.sparta.scheduleapp.entity.Schedule;
import com.sparta.scheduleapp.schedule.dto.request.CreateScheduleRequestDto;
import com.sparta.scheduleapp.schedule.dto.response.CreateScheduleResponseDto;
import com.sparta.scheduleapp.schedule.dto.response.ResponseDto;
import com.sparta.scheduleapp.schedule.dto.response.RetrieveListSchedulesResponseDto;
import com.sparta.scheduleapp.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;


    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public ResponseDto createSchedule(CreateScheduleRequestDto reqDto) {
        Schedule schedule = new Schedule(reqDto.getUserName(),
                reqDto.getTitle(),
                reqDto.getContent(),
                reqDto.getStartDate(),
                reqDto.getEndDate()
        );
        scheduleRepository.save(schedule);
        return new CreateScheduleResponseDto("일정을 성공적으로 등록하였습니다.", schedule.getScheduleId());
    }

    @Transactional
    public ResponseDto retrieveAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();

        return new RetrieveListSchedulesResponseDto("일정을 성공적으로 조회하였습니다.", schedules);
    }
}
