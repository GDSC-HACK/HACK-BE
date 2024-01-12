package com.gdsc.hack.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APITestController {

    @GetMapping("/test")
    @Operation(description = "테스트용 API입니다.")
    public String test() {
        return "good";
    }
}
