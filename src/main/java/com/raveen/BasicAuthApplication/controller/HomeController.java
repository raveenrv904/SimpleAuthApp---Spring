package com.raveen.BasicAuthApplication.controller;


import com.raveen.BasicAuthApplication.dto.ResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/api/home")
    public ResponseDto home(Authentication authentication) {
        String email = authentication.getName(); // Extracted from JWT
        return new ResponseDto(true, "Welcome to the home page!", email);
    }
}
