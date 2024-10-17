package com.sparta.scheduleapp.schedule.controller;

import com.sparta.scheduleapp.schedule.dto.request.CreateRequestDto;
import com.sparta.scheduleapp.schedule.dto.request.EditRequestDto;
import com.sparta.scheduleapp.schedule.dto.response.ResponseDto;
import com.sparta.scheduleapp.schedule.service.ScheduleService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ResponseDto> createSchedule(@RequestBody @Valid CreateRequestDto reqDto) {

        ResponseDto resDto = scheduleService.createSchedule(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
    }

    @GetMapping("/schedule")
    public ResponseEntity<ResponseDto> retrieveAllSchedule(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
//            @RequestParam("sortBy") int sortBy, 수정일로 고정
//            @RequestParam("isAsc") int isAsc 내림차순으로 고정
            ) {

        ResponseDto resDto = scheduleService.retrieveAllSchedules(page - 1, size);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<ResponseDto> retrieveSchedule(@PathVariable Long scheduleId) {

        ResponseDto resDto = scheduleService.retrieveSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @PutMapping("/schedule/{scheduleId}")
    public ResponseEntity<ResponseDto> editSchedule(@PathVariable Long scheduleId, @RequestBody @Valid EditRequestDto reqDto) {

        ResponseDto responseDto = scheduleService.editSchedule(scheduleId, reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/schedule/{scheduleId}")
    public ResponseEntity<ResponseDto> deleteSchedule(@PathVariable Long scheduleId) {

        ResponseDto responseDto = scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
