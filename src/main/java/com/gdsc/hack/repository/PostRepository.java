package com.gdsc.hack.repository;

import com.gdsc.hack.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
