package com.gdsc.hack.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MapNodeRequestDto {
	@NotBlank(message = "restaurantName 필드는 비어있으면 안 됩니다.")
	private String restaurantName;

	@NotNull(message = "latitude 필드는 비어있으면 안 됩니다.")
	private Double latitude;

	@NotNull(message = "longitude 필드는 비어있으면 안 됩니다.")
	private Double longitude;
}
