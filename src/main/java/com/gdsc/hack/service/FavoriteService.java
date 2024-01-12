package com.gdsc.hack.service;

import com.gdsc.hack.domain.Favorite;
import com.gdsc.hack.domain.User;
import com.gdsc.hack.dto.UserDTO;
import com.gdsc.hack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteService favoriteService;
    private final UserRepository userRepository;
    private final ;

    public FavoriteDTO toggleMemberFavorite(Long postId, UserDTO userDTO){
        // 게시물 찾기


        // 유저 정보 가져오기
        User user = userRepository.findByEmail(userDTO.getEmail());

        // 찜하기 여부 체크
        use


        // 찜하기 여부확인후 저장


    }
}
