package com.gdsc.hack.service;

import com.gdsc.hack.domain.Post;
import com.gdsc.hack.domain.User;
import com.gdsc.hack.dto.request.PostEditRequestDto;
import com.gdsc.hack.dto.request.PostRequestDto;
import com.gdsc.hack.repository.PostRepository;
import com.gdsc.hack.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Post writePost(PostRequestDto dto) {
        // 예외처리가 필요합니다. auth 수정되면 그 다음에 예외처리 시작
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(()->new IllegalArgumentException("writePost: 유저가 존재하지 않습니다."));
        Post post = Post
                .builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .build();

        return postRepository.save(post);
    }

    @Transactional
    public void editPost(PostEditRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(()-> new IllegalArgumentException("editPost: 유저를 찾을 수 없습니다."));
        Post post = postRepository
                .findById(dto.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Post 수정 실패: 해당 id와 매칭되는 엔티티가 없습니다."));

        post.checkUserAndUpdateColumn(user, dto.getTitle(), dto.getContent());
    }
}
