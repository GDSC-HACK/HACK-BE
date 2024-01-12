package com.gdsc.hack.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class FoodMapRequestDto {
	private List<MapNodeRequestDto> mapNodeList;
}