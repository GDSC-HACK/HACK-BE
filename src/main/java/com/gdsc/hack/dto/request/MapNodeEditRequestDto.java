package com.gdsc.hack.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MapNodeEditRequestDto {
	private Long id;
	private String email;
	private String restaurantName;
	private Double latitude;
	private Double longitude;
}
