package com.sparta.scheduleapp.user.service;

import com.sparta.scheduleapp.common.config.PasswordEncoder;
import com.sparta.scheduleapp.common.jwt.JwtUtil;
import com.sparta.scheduleapp.entity.User;
import com.sparta.scheduleapp.entity.UserRoleEnum;
import com.sparta.scheduleapp.schedule.repository.ScheduleRepository;
import com.sparta.scheduleapp.schedule.repository.UserScheduleRepository;
import com.sparta.scheduleapp.user.dto.request.CreateUserRequestDto;
import com.sparta.scheduleapp.user.dto.request.EditUserRequestDto;
import com.sparta.scheduleapp.user.dto.request.LoginRequestDto;
import com.sparta.scheduleapp.user.dto.response.*;
import com.sparta.scheduleapp.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserScheduleRepository userScheduleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository,
                       ScheduleRepository scheduleRepository,
                       UserScheduleRepository userScheduleRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
        this.userScheduleRepository = userScheduleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    @Transactional
    public ResponseDto addUser(CreateUserRequestDto reqDto, HttpServletResponse res) {

        userRepository.findByUserName(reqDto.getUserName()).ifPresent(user -> {
            throw new IllegalStateException("해당 사용자 이름이 이미 존재합니다.");
        });

        userRepository.findByEmail(reqDto.getEmail()).ifPresent(user -> {
            throw new IllegalStateException("해당 이메일이 이미 존재합니다.");
        });

        User user = new User(
                reqDto.getUserName(),
                passwordEncoder.encode(reqDto.getPassword()),
                reqDto.getEmail(),
                reqDto.getGender(),
                UserRoleEnum.USER // 하드 코딩
        );

        userRepository.save(user);

        // 응답 Header에 Jwt 추가
        String token = jwtUtil.createToken(user.getUserId(), user.getRole());
        res.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);

        return new AddUserResponseDto("회원가입을 성공적으로 수행하였습니다.", user.getUserId());
    }

    public void login(LoginRequestDto reqDto, HttpServletResponse res) {
        User user = userRepository.findByEmail(reqDto.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다."));

        if(!passwordEncoder.matches(reqDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtUtil.createToken(user.getUserId(), user.getRole());
        res.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
    }

    public ResponseDto retrieveAllUsers() {

        List<User> users = userRepository.findAll();
        return new RetrieveUserLIstResponseDto("전체 유저를 성공적으로 조회하였습니다.", users);
    }

    public ResponseDto retrieveUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));
        return new RetrieveUserResponseDto("유저를 성공적으로 조회하였습니다.", user);
    }

    @Transactional
    public ResponseDto editUser(Long userId, Long jwtUserId, EditUserRequestDto reqDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));
        if (!user.getUserId().equals(jwtUserId)) {
            throw new IllegalArgumentException("본인 정보만 수정할 수 있습니다.");
        }

        user.setUserName(reqDto.getUserName());
        user.setPassword(passwordEncoder.encode(reqDto.getPassword()));
        user.setGender(reqDto.getGender());

        userRepository.save(user);
        return new EditUserResponseDto("유저 정보를 성공적으로 수정하였습니다.", user);
    }

    @Transactional
    public ResponseDto deleteUser(Long userId, Long jwtUserId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));

        if (!user.getUserId().equals(jwtUserId)) {
            throw new IllegalArgumentException("본인만 삭제할 수 있습니다.");
        }

        userRepository.delete(user);

        return new DeleteUserResponseDto("유저가 성공적으로 삭제되었습니다.", userId);
    }

}
