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
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final FoodMapService foodMapService;
    private final MapNodeService mapNodeService;

    @PostMapping("/write")
    @Operation(description = "게시글을 작성하는 api입니다.")
    public ResponseDto<Void> post(
        @RequestBody @Valid PostRequestDto requestDto
    ) {
        Post post = postService.writePost(requestDto);
        FoodMap foodMap = foodMapService.writeFoodMap(post);
        List<MapNodeRequestDto> mapNodeDtoList = requestDto.getFoodMap().getMapNodeList();

        mapNodeService.writeMapNode(foodMap, mapNodeDtoList);

        return ResponseDto.create("저장 완료: 게시글이 성공적으로 작성되었습니다.");
    }

    @PutMapping("/edit")
    @Operation(description = "게시글을 수정하는 API입니다.")
    public ResponseDto<Void> editPost(
        @RequestBody @Valid PostEditRequestDto requestDto
    ) {
        postService.editPost(requestDto);
        List<MapNodeEditRequestDto> mapNodeList = requestDto.getFoodMap().getMapNodeList();

        mapNodeService.editMapNode(mapNodeList);

        return ResponseDto.create("수정 완료: 게시글이 성공적으로 수정되었습니다.");
    }

    @GetMapping("/list")
    @Operation(description = "전체 게시글을 가져오는 API입니다.")
    public ResponseDto<List<PostGetResponseDto>> getPostList() {
        List<PostGetResponseDto> postList = postService.getPostList();

        return ResponseDto.create("커뮤니티 글 가져오기 성공: 게시글들을 성공적으로 가져왔습니다.", postList);
    }

    @GetMapping("/detail/{id}")
    @Operation(description = "특정 게시글의 상세정보를 가져오는 API입니다. 아래 id값에 해당하는 게시글을 가져옵니다.")
    public ResponseDto<PostDetailResponseDto> getPostDetail(
        @PathVariable Long id
    ) {
        PostDetailResponseDto postDetail = postService.getPostDetail(id);

        return ResponseDto.create("상세 조회 성공: 게시글을 성공적으로 가져왔습니다.", postDetail);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "게시물을 삭제하는 API입니다.")
    public ResponseDto<Void> deletePost(
        @PathVariable Long id
    ) {
        postService.delete(id);

        return ResponseDto.create("게시글 삭제 성공: 성공적으로 게시글이 삭제되었습니다.");
    }
}
