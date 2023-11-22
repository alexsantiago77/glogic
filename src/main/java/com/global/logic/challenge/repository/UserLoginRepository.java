package com.global.logic.challenge.repository;

import com.global.logic.challenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserLoginRepository extends JpaRepository<User, String> {
    User getByEmail(String email);
}
