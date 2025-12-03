package com.example.loopie.Users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.loopie.Users.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    java.util.Optional<User> findByUsername(String username);
}
