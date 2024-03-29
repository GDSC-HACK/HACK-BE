package com.gdsc.hack.service;

import com.gdsc.hack.domain.User;

import com.gdsc.hack.dto.TokenDTO;
import com.gdsc.hack.dto.UserDTO;
import com.gdsc.hack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public User createUser(UserDTO userDTO){
        if(userDTO==null || userDTO.getEmail() ==null){
            throw new RuntimeException("Invalid");
        }

        User user=User.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encrypt(userDTO.getEmail(), userDTO.getPassword()))
                .nickname(userDTO.getNickname())
                .build();

        if(userRepository.existsByNickname(user.getNickname())){
            throw new RuntimeException("이미 존재하는 닉네임입니다.");
        }

        User savedUser = userRepository.save(user);

        return savedUser;
    }


    public UserDTO signInUser(UserDTO userDTO){
        User user = getByCredentials(userDTO.getEmail(), userDTO.getPassword());
        if(user !=null){
            final TokenDTO token=tokenProvider.createToken(user);
            return UserDTO.builder()
                    .nickname(user.getNickname())
                    .id(user.getId())
                    .accessToken(token.getAccessToken())
//                    .refreshToken(token.getRefreshToken())
                    .build();
        }
        return null;
    }

    private User getByCredentials(String email, String password) {
        final User originalUser=userRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("getByCredentials: 유저의 이메일을 찾을 수 없습니다."));


        if(originalUser!=null && passwordEncoder.encrypt(email,password).equals(originalUser.getPassword())) {
            log.info("getByCredentials: 사용자 인증 완료");
            return originalUser;
        }

        return null;
    }

}
