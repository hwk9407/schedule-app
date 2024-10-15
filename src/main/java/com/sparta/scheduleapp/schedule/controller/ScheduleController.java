package com.sparta.scheduleapp.schedule.controller;

import com.sparta.scheduleapp.schedule.dto.request.CreateRequestDto;
import com.sparta.scheduleapp.schedule.dto.request.EditRequestDto;
import com.sparta.scheduleapp.schedule.dto.response.ResponseDto;
import com.sparta.scheduleapp.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @PostMapping("/schedule")
    public ResponseEntity<ResponseDto> createSchedule(@RequestBody CreateRequestDto reqDto) {

        ResponseDto resDto = scheduleService.createSchedule(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
    }

    @GetMapping("/schedule")
    public ResponseEntity<ResponseDto> retrieveAllSchedule() {

        ResponseDto resDto = scheduleService.retrieveAllSchedules();
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<ResponseDto> retrieveSchedule(@PathVariable Long scheduleId) {

        ResponseDto resDto = scheduleService.retrieveSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @PutMapping("/schedule/{scheduleId}")
    public ResponseEntity<ResponseDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody EditRequestDto reqDto) {

        ResponseDto responseDto = scheduleService.updateSchedule(scheduleId, reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/schedule/{scheduleId}")
    public ResponseEntity<ResponseDto> deleteSchedule(@PathVariable Long scheduleId) {

        ResponseDto responseDto = scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
