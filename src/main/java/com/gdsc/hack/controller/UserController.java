package com.gdsc.hack.controller;

import com.gdsc.hack.domain.User;
import com.gdsc.hack.dto.UserDTO;
import com.gdsc.hack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody UserDTO userDTO){
        User user = userService.createUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    //로그인
    @PostMapping("/signup")
    public ResponseEntity<?> signInUser(@RequestBody UserDTO userDTO){
        UserDTO response = userService.signInUser(userDTO);
        return ResponseEntity.ok().body(response);
    }
}
