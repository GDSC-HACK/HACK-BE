package com.gdsc.hack.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MapNodeEditRequestDto {
	@NotNull(message = "id 필드는 비어있어서는 안 됩니다.")
	private Long id;

	@Email(message = "email 필드는 비어있어서는 안 됩니다.")
	private String email;

	@NotBlank(message = "restaurantName 필드는 비어있어서는 안 됩니다.")
	private String restaurantName;

	@NotNull(message = "latitude 필드는 비어있어서는 안 됩니다.")
	private Double latitude;

	@NotNull(message = "longitude 필드는 비어있어서는 안 됩니다.")
	private Double longitude;
}
