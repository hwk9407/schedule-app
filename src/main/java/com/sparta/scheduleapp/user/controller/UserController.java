package com.sparta.scheduleapp.user.controller;

import com.sparta.scheduleapp.user.dto.request.CreateUserRequestDto;
import com.sparta.scheduleapp.user.dto.request.EditUserRequestDto;
import com.sparta.scheduleapp.user.dto.request.LoginRequestDto;
import com.sparta.scheduleapp.user.dto.response.ErrorResponseDto;
import com.sparta.scheduleapp.common.dto.ResponseDto;
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
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto reqDto, HttpServletResponse res) {
        try {
            ResponseDto resDto = userService.login(reqDto, res);
            return ResponseEntity.status(HttpStatus.OK).body(resDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto("로그인 중 에러가 발생하였습니다."));
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
    public ResponseEntity<ResponseDto> editUser(@PathVariable Long userId, @RequestBody @Valid EditUserRequestDto reqDto, @RequestAttribute("userId") Long jwtUserId) {
        ResponseDto resDto = userService.editUser(userId, jwtUserId, reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable Long userId, @RequestAttribute("userId") Long jwtUserId) {
        ResponseDto resDto = userService.deleteUser(userId, jwtUserId);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }
}
