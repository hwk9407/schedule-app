package com.sparta.scheduleapp.user.repository;

import com.sparta.scheduleapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
