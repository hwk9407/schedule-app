package com.sparta.scheduleapp.user.controller;

import com.sparta.scheduleapp.schedule.dto.request.EditRequestDto;
import com.sparta.scheduleapp.user.dto.request.CreateUserRequestDto;
import com.sparta.scheduleapp.user.dto.request.EditUserRequestDto;
import com.sparta.scheduleapp.user.dto.response.ResponseDto;
import com.sparta.scheduleapp.user.dto.response.UserDto;
import com.sparta.scheduleapp.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseDto> addUser(@RequestBody @Valid CreateUserRequestDto reqDto) {

        ResponseDto resDto = userService.addUser(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseDto> retrieveAllUsers() {

        ResponseDto resDto = userService.retrieveAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseDto> retrieveUser(@PathVariable Long userId) {
        ResponseDto resDto = userService.retrieveUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<ResponseDto> editUser(@PathVariable Long userId, @RequestBody @Valid EditUserRequestDto reqDto) {
        ResponseDto resDto = userService.editUser(userId, reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable Long userId) {
        ResponseDto resDto = userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }
}
