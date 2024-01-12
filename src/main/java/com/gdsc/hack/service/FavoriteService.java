package com.gdsc.hack.service;

import com.gdsc.hack.domain.Favorite;
import com.gdsc.hack.domain.User;
import com.gdsc.hack.dto.UserDTO;
import com.gdsc.hack.repository.PostRepository;
import com.gdsc.hack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteService favoriteService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


  +

    public FavoriteDTO toggleMemberFavorite(Long postId, UserDTO userDTO) {
        // 게시물 찾기
        -Post post = postRepository.findById(postId)
                -                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));
        +

                // 유저 정보 가져오기
                -User user = userRepository.findByEmail(userDTO.getEmail())
                -                .
        orElseThrow(() -> new IllegalArgumentException("toggleMemberFavorite: 유저가 존재하지않습니다."));
        +User user = userRepository.findByEmail(userDTO.getEmail());

        // 찜하기 여부 체크
        -Favorite findFavorite = user.getFavorites().stream()
                -                .filter(favorite -> favorite.getPost().getId().equals(postId))
                -                .findFirst()
                -                .orElse(null);
        -
                -        // 찜하기 여부확인후 저장
                        - if (findFavorite == null) {
            -Favorite favorite = new Favorite(user, post);
            -favoriteRepository.save(favorite);
            - return;
            -
        }
        +use

                - favoriteRepository.delete(findFavorite);
        -
    }

-

    public List<FavoriteDTO> findAllFavoriteByUser(UserDTO userDTO) {
        -User user = userRepository.findByEmail(userDTO.getEmail())
                -                .
        orElseThrow(() -> new IllegalArgumentException("findAllFavoriteByUser: 존재하지 않는 유저입니다."));
        +        // 찜하기 여부확인후 저장

                -List < Favorite > favorites = favoriteRepository.findByUser(user);

        - return favorites.stream()
                -                .map(FavoriteDTO::from)
                -                .collect(Collectors.toList());
    }

}
