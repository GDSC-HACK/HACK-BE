package com.gdsc.hack.dto.request;

import com.gdsc.hack.domain.Post;
import com.gdsc.hack.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequestDto {
    public String email;
    public String title;
    public String content;
    public FoodMapRequestDto foodMap;
}