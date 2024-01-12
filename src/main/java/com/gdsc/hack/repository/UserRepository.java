package com.gdsc.hack.repository;

import com.gdsc.hack.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existByNickname(String nickname);
    User findByEmail(String email);
}
