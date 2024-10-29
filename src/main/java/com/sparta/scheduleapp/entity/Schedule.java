package com.sparta.scheduleapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedule")
public class Schedule extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

//    @Column(nullable = false, unique = true)
//    private String userName;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "schedule")
    List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "schedule")
    List<UserSchedule> userSchedules = new ArrayList<>();

    public Schedule(String title, String content, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;

        checkValidDate();
    }

    private void checkValidDate() {

        if (startDate == null) startDate = LocalDateTime.now();
        if (endDate == null) endDate = startDate;
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("종료일은 반드시 시작일보다 이후 날짜여야 합니다.");
        }

    }

    public int getCommentsCount() {
        return comments.size();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setSchedule(this);

    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setSchedule(null); // 부모 외래 키를 수동으로 null로 설정
    }

    public List<User> getUsers() {
        return userSchedules.stream()
                .map(UserSchedule::getUser)
                .toList();
    }
}
