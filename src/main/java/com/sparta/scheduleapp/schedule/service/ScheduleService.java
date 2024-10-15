package com.sparta.scheduleapp.schedule.service;

import com.sparta.scheduleapp.entity.Schedule;
import com.sparta.scheduleapp.schedule.dto.request.CreateRequestDto;
import com.sparta.scheduleapp.schedule.dto.request.EditRequestDto;
import com.sparta.scheduleapp.schedule.dto.response.*;
import com.sparta.scheduleapp.schedule.repository.ScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public ResponseDto createSchedule(CreateRequestDto reqDto) {
        Schedule schedule = new Schedule(reqDto.getUserName(),
                reqDto.getTitle(),
                reqDto.getContent(),
                reqDto.getStartDate(),
                reqDto.getEndDate()
        );
        scheduleRepository.save(schedule);
        return new CreateResponseDto("일정을 성공적으로 등록하였습니다.", schedule.getScheduleId());
    }

    public ResponseDto retrieveAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();

        return new RetrieveListResponseDto("일정을 성공적으로 조회하였습니다.", schedules);
    }

    public ResponseDto retrieveSchedule(Long scheduleId) {
        // 빈 객체 반환 시 에러 상태 코드 404와 함께 처리 해야함
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 일정입니다."));

        return new RetrieveResponseDto("일정을 성공적으로 조회하였습니다.", schedule);
    }

    @Transactional
    public ResponseDto updateSchedule(Long scheduleId, EditRequestDto reqDto) {
        // 빈 객체 반환 시 에러 상태 코드 404와 함께 처리 해야함
        Schedule schedule = scheduleRepository.findByScheduleId(scheduleId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 일정입니다."));

        schedule.setTitle(reqDto.getTitle());
        schedule.setContent(reqDto.getContent());
        schedule.setStartDate(reqDto.getStartDate());
        schedule.setEndDate(reqDto.getEndDate());

        return new EditResponseDto("일정이 성공적으로 수정되었습니다.", schedule);
    }
}
