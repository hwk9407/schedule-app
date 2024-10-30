package com.sparta.scheduleapp.user.controller;

import com.sparta.scheduleapp.common.dto.ErrorResponseDto;
import com.sparta.scheduleapp.common.exception.NotValidRequestException;
import com.sparta.scheduleapp.common.jwt.JwtUtil;
import com.sparta.scheduleapp.user.dto.request.CreateUserRequestDto;
import com.sparta.scheduleapp.user.dto.request.EditUserRequestDto;
import com.sparta.scheduleapp.user.dto.request.LoginRequestDto;
import com.sparta.scheduleapp.user.dto.response.*;
import com.sparta.scheduleapp.common.dto.ResponseDto;
import com.sparta.scheduleapp.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<ResponseDto> addUser(@RequestBody @Valid CreateUserRequestDto reqDto,
                                               Errors errors,
                                               HttpServletResponse res
    ) {
        if (errors.hasErrors()) {
            String field = errors.getFieldError().getField();
            String message = errors.getFieldError().getDefaultMessage();
            throw new NotValidRequestException("ERR001", field + " 필드에 대한 에러 : " + message);
        }

        AddUserWithTokenResponseDto resWithTokenDto = userService.addUser(reqDto);

        // 헤더에 토큰 추가
        res.addHeader(JwtUtil.AUTHORIZATION_HEADER, resWithTokenDto.getToken());

        // 클라이언트에 반환할 Dto로 변환
        AddUserResponseDto resDto = new AddUserResponseDto(resWithTokenDto.getMessage(), resWithTokenDto.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto reqDto, HttpServletResponse res) {
        LoginWithTokenResponseDto resWithTokenDto = userService.login(reqDto);

        // 헤더에 토큰 추가
        res.addHeader(JwtUtil.AUTHORIZATION_HEADER, resWithTokenDto.getToken());

        // 클라이언트에 반환할 Dto로 변환
        LoginResponseDto resDto = new LoginResponseDto(resWithTokenDto.getMessage());

        return ResponseEntity.status(HttpStatus.OK).body(resDto);
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
    public ResponseEntity<ResponseDto> editUser(@PathVariable Long userId,
                                                @RequestBody @Valid EditUserRequestDto reqDto,
                                                Errors errors,
                                                @RequestAttribute("userId") Long jwtUserId
    ) {
        if (errors.hasErrors()) {
            String field = errors.getFieldError().getField();
            String message = errors.getFieldError().getDefaultMessage();
            throw new NotValidRequestException("ERR001", field + " 필드에 대한 에러 : " + message);
        }

        ResponseDto resDto = userService.editUser(userId, jwtUserId, reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable Long userId, @RequestAttribute("userId") Long jwtUserId) {
        ResponseDto resDto = userService.deleteUser(userId, jwtUserId);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }
}
