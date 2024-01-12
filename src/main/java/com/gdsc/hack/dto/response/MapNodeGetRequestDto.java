package com.gdsc.hack.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MapNodeGetRequestDto {
	private String restaurantName;
	private Double latitude;
	private Double longitude;
}
