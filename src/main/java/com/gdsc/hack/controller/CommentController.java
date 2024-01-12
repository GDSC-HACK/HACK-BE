package com.gdsc.hack.controller;

import com.gdsc.hack.dto.UserDTO;
import com.gdsc.hack.global.JwtResolver;
import com.gdsc.hack.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}")
    public Long addComment(@JwtResolver UserDTO userDTO, @PathVariable Long postId, @RequestBody String content) {
        return commentService.addComment(userDTO, postId, content);
    }
}
