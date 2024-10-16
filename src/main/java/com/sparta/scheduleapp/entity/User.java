package com.sparta.scheduleapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "유효한 이메일 주소를 입력하세요.")
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE) // 유저가 삭제되면 연결된 중간테이블 데이터 삭제
    List<UserSchedule> userSchedules = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE) // 유저가 삭제되면 연결된 댓글 데이터 삭제
    List<Comment> comments = new ArrayList<>();

    public User(String userName, String email, Gender gender) {
        this.userName = userName;
        this.email = email;
        this.gender = gender;
    }

    public List<Schedule> getSchedules() {
        return userSchedules.stream()
                .map(UserSchedule::getSchedule)
                .toList();
    }
}
