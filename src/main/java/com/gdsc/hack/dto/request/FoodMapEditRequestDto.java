package com.gdsc.hack.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodMapEditRequestDto {
	private List<MapNodeEditRequestDto> mapNodeList;
}
