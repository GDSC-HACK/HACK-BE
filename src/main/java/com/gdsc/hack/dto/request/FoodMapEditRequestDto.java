package com.gdsc.hack.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodMapEditRequestDto {
	@NotNull(message = "mapNodeList는 비어있으면 안 됩니다.")
	private List<MapNodeEditRequestDto> mapNodeList;
}
