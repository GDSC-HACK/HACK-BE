package com.gdsc.hack.controller;

import com.gdsc.hack.dto.UserDTO;
import com.gdsc.hack.global.JwtResolver;
import com.gdsc.hack.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping()
    public ResponseEntity<?> addMemberFavorite(@RequestBody Map<String,Long> postId, @JwtResolver UserDTO userDTO){
        return favoriteService.toggleMemberFavorite(postId.get("postId"), userDTO);
    }

    // 유저가 찜한 게시물 불러오기
}
