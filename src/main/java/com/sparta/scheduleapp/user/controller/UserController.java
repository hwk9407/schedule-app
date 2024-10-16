package com.sparta.scheduleapp.user.controller;

import com.sparta.scheduleapp.user.dto.request.CreateUserRequestDto;
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

    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseDto> retrieveUser(@PathVariable Long id) {
        ResponseDto resDto = userService.retrieveUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

/*
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        ResponseDto resDto = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable Long id) {
        ResponseDto resDto = userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }*/
}
