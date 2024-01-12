package com.gdsc.hack.service;

import com.gdsc.hack.domain.Comment;
import com.gdsc.hack.domain.MapNode;
import com.gdsc.hack.domain.Post;
import com.gdsc.hack.domain.User;
import com.gdsc.hack.dto.request.PostEditRequestDto;
import com.gdsc.hack.dto.request.PostRequestDto;
import com.gdsc.hack.dto.response.*;
import com.gdsc.hack.repository.PostRepository;
import com.gdsc.hack.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(()->new IllegalArgumentException("writePost: 유저가 존재하지 않습니다."));
        Post post = Post
                .builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .favorites(new ArrayList<>())
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

    public List<PostGetResponseDto> getPostList() {
        List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

		return postList
                .stream()
                .map(post -> {
                    FoodMapGetResponseDto foodMapGetResponseDto = post.getFoodMap().toGetDto();

					return PostGetResponseDto
                            .builder()
                            .post_id(post.getId())
                            .title(post.getTitle())
                            .content(post.getContent())
                            .userName(post.getUser().getNickname())
                            .foodMap(foodMapGetResponseDto)
                            .commentCnt(post.getCommentList().size())
                            .build();
                })
                .toList();
    }

    public PostDetailResponseDto getPostDetail(Long id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("상세 정보 조회 실패: 존재하지 않는 게시물입니다."));

        List<MapNodeGetRequestDto> mapNodeRequestDtoList = post.getFoodMap().getMapNodeList()
                .stream()
                .map(MapNode::toGetDto)
                .toList();

        FoodMapGetResponseDto foodMapGetResponseDto = FoodMapGetResponseDto
                .builder()
                .mapNodeList(mapNodeRequestDtoList)
                .build();

        List<CommentDetailResponseDto> commentDetailDtoList = post.getCommentList()
                .stream()
                .map(Comment::toDetailDto)
                .toList();

        return PostDetailResponseDto
                .builder()
                .postId(post.getId())
                .authorName(post.getUser().getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .foodMap(foodMapGetResponseDto)
                .commentList(commentDetailDtoList)
                .build();
    }

    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글 삭제 실패: 해당하는 게시글이 없습니다."));

        postRepository.delete(post);
    }
}
