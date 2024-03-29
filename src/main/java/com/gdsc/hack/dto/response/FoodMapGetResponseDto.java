package com.gdsc.hack.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class FoodMapGetResponseDto {
	private List<MapNodeGetRequestDto> mapNodeList;
}
