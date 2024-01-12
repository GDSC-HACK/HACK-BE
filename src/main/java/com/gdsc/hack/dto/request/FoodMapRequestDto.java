package com.gdsc.hack.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class FoodMapRequestDto {
	@NotNull(message = "mapNodeList는 비어있으면 안 됩니다.")
	private List<MapNodeRequestDto> mapNodeList;
}