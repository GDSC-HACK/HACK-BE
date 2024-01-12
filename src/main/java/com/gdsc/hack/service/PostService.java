package com.gdsc.hack.service;

import com.gdsc.hack.domain.FoodMap;
import com.gdsc.hack.domain.MapNode;
import com.gdsc.hack.domain.Post;
import com.gdsc.hack.domain.User;
import com.gdsc.hack.dto.request.MapNodeRequestDto;
import com.gdsc.hack.dto.request.PostRequestDto;
import com.gdsc.hack.repository.FoodMapRepository;
import com.gdsc.hack.repository.MapNodeRepository;
import com.gdsc.hack.repository.PostRepository;
import com.gdsc.hack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Post writePost(PostRequestDto dto) {
        // 예외처리가 필요합니다. auth 수정되면 그 다음에 예외처리 시작
        User user = userRepository.findByEmail(dto.getEmail());
        Post post = Post
                .builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .build();

        return postRepository.save(post);
    }

}
