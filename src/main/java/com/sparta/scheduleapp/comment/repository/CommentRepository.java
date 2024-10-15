package com.sparta.scheduleapp.comment.repository;

import com.sparta.scheduleapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
