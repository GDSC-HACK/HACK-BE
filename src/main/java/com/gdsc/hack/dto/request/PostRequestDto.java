package com.gdsc.hack.dto.request;

import com.gdsc.hack.domain.Post;
import com.gdsc.hack.domain.User;

import java.util.List;

public record PostRequestDto(
    String email,
    String title,
    String content,
    FoodMapRequestDto foodMap
) {
    public Post toEntity(User user) {
        return Post
                .builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }
}

record FoodMapRequestDto(
    List<MapNodeRequestDto> mapNodeList
) {}

record MapNodeRequestDto(
    String restaurantName,
    Double latitude,
    Double longitude
) {}