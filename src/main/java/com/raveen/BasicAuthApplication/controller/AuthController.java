package com.raveen.BasicAuthApplication.controller;


import com.raveen.BasicAuthApplication.dto.ResponseDto;
import com.raveen.BasicAuthApplication.dto.SigninRequest;
import com.raveen.BasicAuthApplication.dto.SignupRequest;
import com.raveen.BasicAuthApplication.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupRequest request)  {
        return authService.signup(request);
    }

    @PostMapping("/signin")
    public ResponseDto signin(@RequestBody SigninRequest request) {
        return authService.signin(request);
    }
}
