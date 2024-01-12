package com.gdsc.hack.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class FoodMapEditRequestDto {
	private List<MapNodeEditRequestDto> mapNodeList;
}
