package com.gdsc.hack.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEditRequestDto {
	private String email;
	private Long postId;
	private String title;
	private String content;
	private FoodMapEditRequestDto foodMap;
}
