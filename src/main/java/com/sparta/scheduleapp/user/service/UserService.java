package com.sparta.scheduleapp.user.service;

import com.sparta.scheduleapp.common.config.PasswordEncoder;
import com.sparta.scheduleapp.common.dto.ResponseDto;
import com.sparta.scheduleapp.common.exception.ClientBadRequestException;
import com.sparta.scheduleapp.common.exception.LoginAuthorizationException;
import com.sparta.scheduleapp.common.exception.ResourceNotFoundException;
import com.sparta.scheduleapp.common.exception.UserAccessDeniedException;
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
    public AddUserWithTokenResponseDto addUser(CreateUserRequestDto reqDto) {

        userRepository.findByUserName(reqDto.getUserName()).ifPresent(user -> {
            throw new ClientBadRequestException("ERR004", "해당 사용자 이름이 이미 존재합니다.");
        });

        userRepository.findByEmail(reqDto.getEmail()).ifPresent(user -> {
            throw new ClientBadRequestException("ERR004", "해당 이메일이 이미 존재합니다.");
        });

        User user = new User(
                reqDto.getUserName(),
                passwordEncoder.encode(reqDto.getPassword()),
                reqDto.getEmail(),
                reqDto.getGender(),
                UserRoleEnum.USER // 하드 코딩
        );

        userRepository.save(user);

        // 회원가입을 성공 시 jwt 토큰을 헤더에 넣어주기 위해 생성
        String token = jwtUtil.createToken(user.getUserId(), user.getRole());

        return new AddUserWithTokenResponseDto("회원가입을 성공적으로 수행하였습니다.", user.getUserId(), token);
    }

    public LoginWithTokenResponseDto login(LoginRequestDto reqDto) {
        User user = userRepository.findByEmail(reqDto.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("ERR002", "존재하지 않는 유저입니다."));

        if(!passwordEncoder.matches(reqDto.getPassword(), user.getPassword())) {
            throw new LoginAuthorizationException("ERR005", "비밀번호가 일치하지 않습니다.");
        }

        // Service 계층에서 HttpServletResponse 를 의존하는 형태는 적합하지 않음.
        // 처리를 해야 한다면 Controlller 계층에서 처리하는 것이 적합함.
        String token = jwtUtil.createToken(user.getUserId(), user.getRole());

        return new LoginWithTokenResponseDto("로그인을 성공하였습니다.", token);
    }

    public ResponseDto retrieveAllUsers() {

        List<User> users = userRepository.findAll();
        return new RetrieveUserLIstResponseDto("전체 유저를 성공적으로 조회하였습니다.", users);
    }

    public ResponseDto retrieveUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("ERR002", "존재하지 않는 유저입니다.")
        );
        return new RetrieveUserResponseDto("유저를 성공적으로 조회하였습니다.", user);
    }

    @Transactional
    public ResponseDto editUser(Long userId, Long jwtUserId, EditUserRequestDto reqDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("ERR002", "존재하지 않는 유저입니다.")
        );
        User loginUser = userRepository.findById(jwtUserId).orElseThrow(
                () -> new ResourceNotFoundException("ERR002", "존재하지 않는 유저입니다.")
        );
        if (!user.equals(loginUser)) {
            throw new UserAccessDeniedException("ERR003", "본인 정보만 수정할 수 있습니다.");
        }

        checkSameUserName(reqDto.getUserName());
        user.edit(
                reqDto.getUserName(),
                passwordEncoder.encode(reqDto.getPassword()),
                reqDto.getGender()
        );

        userRepository.save(user);
        return new EditUserResponseDto("유저 정보를 성공적으로 수정하였습니다.", user);
    }

    @Transactional
    public ResponseDto deleteUser(Long userId, Long jwtUserId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("ERR002", "존재하지 않는 유저입니다.")
        );

        if (!user.getUserId().equals(jwtUserId)) {
            throw new UserAccessDeniedException("ERR003", "본인만 삭제할 수 있습니다.");
        }

        userRepository.delete(user);

        return new DeleteUserResponseDto("유저가 성공적으로 삭제되었습니다.", userId);
    }

    private void checkSameUserName(String changeName) {
        boolean duplicationFlag = userRepository.findByUserName(changeName).isPresent();
        if (duplicationFlag) {
            throw new ClientBadRequestException("ERR004", "이미 존재하는 이름입니다.");
        }
    }
}
