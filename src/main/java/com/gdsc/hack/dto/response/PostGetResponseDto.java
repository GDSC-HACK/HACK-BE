package com.gdsc.hack.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostGetResponseDto {
	private Long post_id;
	private String title;
	private String content;
	private String userName;
	private FoodMapGetResponseDto foodMap;
	private int commentCnt;
}
