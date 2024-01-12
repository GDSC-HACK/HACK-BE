package com.gdsc.hack.controller;

import com.gdsc.hack.domain.FoodMap;
import com.gdsc.hack.domain.Post;
import com.gdsc.hack.dto.request.MapNodeEditRequestDto;
import com.gdsc.hack.dto.request.MapNodeRequestDto;
import com.gdsc.hack.dto.request.PostEditRequestDto;
import com.gdsc.hack.dto.request.PostRequestDto;
import com.gdsc.hack.dto.response.PostDetailResponseDto;
import com.gdsc.hack.dto.response.PostGetResponseDto;
import com.gdsc.hack.global.dto.ResponseDto;
import com.gdsc.hack.service.FoodMapService;
import com.gdsc.hack.service.MapNodeService;
import com.gdsc.hack.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final FoodMapService foodMapService;
    private final MapNodeService mapNodeService;

    @PostMapping("/write")
    public ResponseDto<Void> post(
        @RequestBody @Valid PostRequestDto requestDto
    ) {
        Post post = postService.writePost(requestDto);
        FoodMap foodMap = foodMapService.writeFoodMap(post);
        List<MapNodeRequestDto> mapNodeDtoList = requestDto.getFoodMap().getMapNodeList();

        mapNodeService.writeMapNode(foodMap, mapNodeDtoList);

        return ResponseDto.success("저장 완료: 게시글이 성공적으로 작성되었습니다.");
    }

    @PutMapping("/edit")
    public ResponseDto<Void> editPost(
        @RequestBody @Valid PostEditRequestDto requestDto
    ) {
        postService.editPost(requestDto);
        List<MapNodeEditRequestDto> mapNodeList = requestDto.getFoodMap().getMapNodeList();

        mapNodeService.editMapNode(mapNodeList);

        return ResponseDto.success("수정 완료: 게시글이 성공적으로 수정되었습니다.");
    }

    @GetMapping("/list")
    public ResponseDto<List<PostGetResponseDto>> getPostList() {
        List<PostGetResponseDto> postList = postService.getPostList();

        return ResponseDto.success("커뮤니티 글 가져오기 성공: 게시글들을 성공적으로 가져왔습니다.", postList);
    }

    @GetMapping("/detail/{id}")
    public ResponseDto<PostDetailResponseDto> getPostDetail(
        @PathVariable Long id
    ) {
        PostDetailResponseDto postDetail = postService.getPostDetail(id);

        return ResponseDto.success("상세 조회 성공: 게시글을 성공적으로 가져왔습니다.", postDetail);
    }
}
