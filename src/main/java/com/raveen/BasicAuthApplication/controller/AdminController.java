package com.raveen.BasicAuthApplication.controller;


import com.raveen.BasicAuthApplication.dto.ResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public ResponseDto adminDashboard(Authentication authentication) {
        return new ResponseDto(true, "Welcome Admin: " + authentication.getName(), null);
    }
}
