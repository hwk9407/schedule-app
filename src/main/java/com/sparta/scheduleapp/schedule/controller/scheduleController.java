package com.sparta.scheduleapp.schedule.controller;

import com.sparta.scheduleapp.schedule.dto.request.CreateScheduleRequestDto;
import com.sparta.scheduleapp.schedule.dto.response.ResponseDto;
import com.sparta.scheduleapp.schedule.dto.response.RetrieveListSchedulesResponseDto;
import com.sparta.scheduleapp.schedule.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api")
@RestController
public class scheduleController {

    private final ScheduleService scheduleService;

    public scheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @PostMapping("/schedule")
    public ResponseEntity<ResponseDto> createSchedule(@RequestBody CreateScheduleRequestDto reqDto) {
        ResponseDto resDto = scheduleService.createSchedule(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
    }

    @GetMapping("/schedule")
    public ResponseEntity<ResponseDto> retrieveAllSchedule() {
        ResponseDto resDto = scheduleService.retrieveAllSchedules();

        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

}
