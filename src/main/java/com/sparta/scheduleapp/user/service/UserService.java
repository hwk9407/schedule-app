package com.sparta.scheduleapp.user.service;

import com.sparta.scheduleapp.entity.User;
import com.sparta.scheduleapp.schedule.repository.ScheduleRepository;
import com.sparta.scheduleapp.schedule.repository.UserScheduleRepository;
import com.sparta.scheduleapp.user.dto.request.CreateUserRequestDto;
import com.sparta.scheduleapp.user.dto.request.EditUserRequestDto;
import com.sparta.scheduleapp.user.dto.response.*;
import com.sparta.scheduleapp.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserScheduleRepository userScheduleRepository;

    public UserService(UserRepository userRepository, ScheduleRepository scheduleRepository, UserScheduleRepository userScheduleRepository) {
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
        this.userScheduleRepository = userScheduleRepository;
    }


    @Transactional
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
        return new RetrieveUserLIstResponseDto("전체 유저를 성공적으로 조회하였습니다.", users);
    }

    public ResponseDto retrieveUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));
        return new RetrieveUserResponseDto("유저를 성공적으로 조회하였습니다.", user);
    }

    @Transactional
    public ResponseDto editUser(Long userId, EditUserRequestDto reqDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));
        user.setUserName(reqDto.getUserName());
        user.setEmail(reqDto.getEmail());
        user.setGender(reqDto.getGender());

        userRepository.save(user);
        return new EditUserResponseDto("유저 정보를 성공적으로 수정하였습니다.", user);
    }

    @Transactional
    public ResponseDto deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));

        userRepository.delete(user);

        // 유저가 관련되어있는 일정 삭제.
        /*for (UserSchedule userschedule : user.getUserSchedules()) {
            Schedule schedule = userschedule.getSchedule();
            long userCount = userScheduleRepository.countBySchedule(schedule);

            if (userCount == 1) {
                scheduleRepository.delete(schedule);
            }
        }*/

        return new DeleteUserResponseDto("유저가 성공적으로 삭제되었습니다.", userId);
    }
}
