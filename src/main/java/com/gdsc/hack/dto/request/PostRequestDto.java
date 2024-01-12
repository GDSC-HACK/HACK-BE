package com.gdsc.hack.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequestDto {
    @Email(message = "email 필드는 비어있으면 안 됩니다.")
    private String email;

    @NotBlank(message = "title 필드는 비어있으면 안 됩니다.")
    private String title;

    @NotBlank(message = "content 필드는 비어있으면 안 됩니다.")
    private String content;

    @NotNull(message = "foodMap 필드는 비어있으면 안 됩니다.")
    private FoodMapRequestDto foodMap;
}