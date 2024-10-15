package com.sparta.scheduleapp.comment.repository;

import com.sparta.scheduleapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> getByCommentId(Long commentId);
}
