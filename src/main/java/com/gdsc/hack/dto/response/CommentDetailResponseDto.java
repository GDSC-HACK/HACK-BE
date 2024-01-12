package com.gdsc.hack.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommentDetailResponseDto {
	private Long commentId;
	private String author;
	private String content;
}
