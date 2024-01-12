package com.gdsc.hack.repository;

import com.gdsc.hack.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByNickname(String nickname);
    Optional<User> findByEmail(String email);
}
