package com.sparta.scheduleapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;


    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "scheduleId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Comment(Schedule schedule, User user, String content) {
        this.schedule = schedule;
        this.user = user;
        this.content = content;
    }
}
