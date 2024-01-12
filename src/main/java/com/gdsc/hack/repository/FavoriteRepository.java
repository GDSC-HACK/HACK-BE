package com.gdsc.hack.repository;

import com.gdsc.hack.domain.Favorite;
import com.gdsc.hack.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    List<Favorite> findByUser(User user);
}
