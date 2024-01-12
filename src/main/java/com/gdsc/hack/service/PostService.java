package com.gdsc.hack.service;

import com.gdsc.hack.domain.Post;
import com.gdsc.hack.domain.User;
import com.gdsc.hack.dto.request.PostRequestDto;
import com.gdsc.hack.repository.PostRepository;
import com.gdsc.hack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void writePost(PostRequestDto dto) {
        // 예외처리가 필요합니다. auth 수정되면 그 다음에 예외처리 시작
        User user = userRepository.findByEmail(dto.email());
        Post post = dto.toEntity(user);

        postRepository.save(post);
    }

}
