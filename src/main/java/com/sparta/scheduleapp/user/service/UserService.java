package com.sparta.scheduleapp.user.service;

import com.sparta.scheduleapp.entity.User;
import com.sparta.scheduleapp.user.dto.request.CreateUserRequestDto;
import com.sparta.scheduleapp.user.dto.response.AddUserResponseDto;
import com.sparta.scheduleapp.user.dto.response.ResponseDto;
import com.sparta.scheduleapp.user.dto.response.RetrieveUserLIstResponseDto;
import com.sparta.scheduleapp.user.dto.response.RetrieveUserResponseDto;
import com.sparta.scheduleapp.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ResponseDto retrieveAllUsers() {

        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
        return new RetrieveUserLIstResponseDto("전체 유저를 성공적으로 조회하였습니다.", users);
    }

    public ResponseDto retrieveUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));
        return new RetrieveUserResponseDto("유저를 성공적으로 조회하였습니다.", user);
    }
}
