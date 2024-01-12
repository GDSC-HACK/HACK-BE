package com.gdsc.hack.service;

import com.gdsc.hack.domain.Favorite;
import com.gdsc.hack.domain.Post;
import com.gdsc.hack.domain.User;
import com.gdsc.hack.dto.UserDTO;
import com.gdsc.hack.repository.FavoriteRepository;
import com.gdsc.hack.repository.PostRepository;
import com.gdsc.hack.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FavoriteServiceTest {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    @DisplayName("찜하기 추가 테스트 ( 찜하지않은 유저는 찜하기, 찜한 유저는 찜 해제 하기)")
    public void addFavorite() {
        User user = User.builder()
                .email("alrax3574@naver.com")
                .password("1234")
                .nickname("nickname")
                .favorites(new ArrayList<>())
                .build();

        User savedUser = userRepository.save(user);


        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .user(user)
                .favorites(new ArrayList<>())
                .build();

        Post savedPost = postRepository.save(post);

        UserDTO userDTO=UserDTO.builder()
                .nickname(savedUser.getNickname())
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .password(savedUser.getPassword())
                .build();

        favoriteService.toggleMemberFavorite(savedPost.getId(), userDTO);

        Favorite result = favoriteRepository.findById(savedPost.getId()).get();

        Assertions.assertNotNull(result);
    }

    @Test
    @DisplayName("유저의 찜목록 가져오기")
    public void getUserFavoriteList(){
        User user = User.builder()
                .email("alrax3574@naver.com")
                .password("1234")
                .nickname("nickname")
                .favorites(new ArrayList<>())
                .build();

        User savedUser = userRepository.save(user);


        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .user(user)
                .favorites(new ArrayList<>())
                .build();

        Post post2 = Post.builder()
                .title("제목")
                .content("내용")
                .user(user)
                .favorites(new ArrayList<>())
                .build();

        Post savedPost = postRepository.save(post);
        Post savedPost2 = postRepository.save(post2);


        Favorite favorite=new Favorite(savedUser,post);
        Favorite favorite2=new Favorite(savedUser,post2);

        favoriteRepository.save(favorite);
        favoriteRepository.save(favorite2);

        Assertions.assertEquals(2,favoriteRepository.findByUser(user).size());

    }


}