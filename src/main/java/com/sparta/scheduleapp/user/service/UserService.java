package com.sparta.scheduleapp.user.service;

import com.sparta.scheduleapp.entity.User;
import com.sparta.scheduleapp.user.dto.request.CreateUserRequestDto;
import com.sparta.scheduleapp.user.dto.response.AddUserResponseDto;
import com.sparta.scheduleapp.user.dto.response.ResponseDto;
import com.sparta.scheduleapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseDto addUser(CreateUserRequestDto reqDto) {
        User user = new User(
                reqDto.getUserName(),
                reqDto.getEmail(),
                reqDto.getGender()
        );
        userRepository.save(user);
        return new AddUserResponseDto("회원가입을 성공적으로 수행하였습니다.", user.getUserId());
    }
}
