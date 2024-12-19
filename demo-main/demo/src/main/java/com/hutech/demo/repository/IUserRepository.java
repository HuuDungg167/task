package com.hutech.demo.repository;

import com.hutech.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByUsernameContainingIgnoreCase(String username);
}
