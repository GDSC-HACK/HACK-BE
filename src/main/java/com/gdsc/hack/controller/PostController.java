package com.gdsc.hack.controller;

import com.gdsc.hack.dto.request.PostRequestDto;
import com.gdsc.hack.dto.response.PostResponseDto;
import com.gdsc.hack.global.dto.ResponseDto;
import com.gdsc.hack.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public ResponseDto<Void> post(
        @RequestBody PostRequestDto requestDto
    ) {
        postService.writePost(requestDto);
        return ResponseDto.success("저장 완료: 게시글이 성공적으로 작성되었습니다.");
    }
}
