package com.gdsc.hack.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PostDetailResponseDto {
	private Long postId;
	private String authorName;
	private String title;
	private String content;
	private FoodMapGetResponseDto foodMap;
	private List<CommentDetailResponseDto> commentList;
}
