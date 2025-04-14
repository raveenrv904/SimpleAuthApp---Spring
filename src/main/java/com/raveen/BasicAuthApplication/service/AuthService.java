package com.raveen.BasicAuthApplication.service;


import com.raveen.BasicAuthApplication.dto.ResponseDto;
import com.raveen.BasicAuthApplication.dto.SigninRequest;
import com.raveen.BasicAuthApplication.dto.SignupRequest;
import com.raveen.BasicAuthApplication.dto.UserDto;
import com.raveen.BasicAuthApplication.model.User;
import com.raveen.BasicAuthApplication.repository.UserRepository;
import com.raveen.BasicAuthApplication.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public ResponseDto signup(SignupRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        UserDto userDto  = new UserDto(user.getUsername(), user.getEmail());

        return new ResponseDto(true, "User Registered Successfully", userDto);
    }

    public ResponseDto signin(SigninRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new RuntimeException("Invalid Email or Password"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Email and Password");
        }
        String token = jwtUtil.generateToken(user.getEmail());

        return new ResponseDto(true, "Login successfull", token);
     }
}
