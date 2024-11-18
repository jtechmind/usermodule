package com.jtech.usermodule.repository;

import com.jtech.usermodule.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Long, User> {

    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
}
