package com.sparta.scheduleapp.user.controller;

import com.sparta.scheduleapp.schedule.dto.request.EditRequestDto;
import com.sparta.scheduleapp.user.dto.request.CreateUserRequestDto;
import com.sparta.scheduleapp.user.dto.request.EditUserRequestDto;
import com.sparta.scheduleapp.user.dto.request.LoginRequestDto;
import com.sparta.scheduleapp.user.dto.response.ResponseDto;
import com.sparta.scheduleapp.user.dto.response.UserDto;
import com.sparta.scheduleapp.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
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

    @PostMapping("/auth/signup")
    public ResponseEntity<ResponseDto> addUser(@RequestBody @Valid CreateUserRequestDto reqDto, HttpServletResponse res) {

        ResponseDto resDto = userService.addUser(reqDto, res);
        return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
    }

    @PostMapping("/auth/login")
    public void login(@RequestBody LoginRequestDto reqDto, HttpServletResponse res) {
        try {
            userService.login(reqDto, res);
        } catch (Exception e) {
            throw new RuntimeException("로그인 중 에러가 발생하였습니다.");
        }
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
