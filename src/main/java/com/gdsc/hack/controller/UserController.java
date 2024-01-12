package com.gdsc.hack.controller;

import com.gdsc.hack.domain.User;
import com.gdsc.hack.dto.UserDTO;
import com.gdsc.hack.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid UserDTO userDTO){
        User user = userService.createUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    //로그인
    @PostMapping("/signin")
    public ResponseEntity<?> signInUser(@RequestBody @Valid UserDTO userDTO){
        UserDTO response = userService.signInUser(userDTO);
        return ResponseEntity.ok().body(response);
    }
}
