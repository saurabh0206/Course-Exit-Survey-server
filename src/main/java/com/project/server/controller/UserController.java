package com.project.server.controller;

import com.project.server.configuration.JwtUtil;
import com.project.server.entity.GoogleLoginRequest;
import com.project.server.entity.LoginRequest;
import com.project.server.entity.LoginResponse;
import com.project.server.entity.User;
import com.project.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // Login user with email and password
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            String token = jwtUtil.doGenerateToken(null, user.getId());
            return ResponseEntity.ok(new LoginResponse(user.getEmail(), token, user.getRole()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseException(null, null, e, e.getMessage(), null));
        }
    }

    // Login user with Google
    @PostMapping("/login/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody GoogleLoginRequest googleLoginRequest) {
        try {
            User user = userService.findByEmail(googleLoginRequest.getData().getEmail());
            if (user == null) {
                return ResponseEntity.status(401).body(new ErrorResponseException(null, null, null,
                        "User currently not enrolled in any course", null));
            }
            String token = jwtUtil.doGenerateToken(null, user.getId());
            return ResponseEntity.ok(new LoginResponse(user.getEmail(), token, user.getRole()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseException(null, null, e, e.getMessage(), null));
        }
    }
}