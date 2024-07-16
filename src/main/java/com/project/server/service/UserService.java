package com.project.server.service;

import com.project.server.configuration.JwtUtil;
import com.project.server.entity.User;
import com.project.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String loginUser(String email, String password) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new Exception("Incorrect email");
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Invalid credentials");
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>());

        return jwtUtil.generateToken(userDetails);
    }

    public String loginWithGoogle(String email) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new Exception("User currently not enrolled in any course");
        }

        User user = userOptional.get();
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>());

        return jwtUtil.generateToken(userDetails);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User login(String email, String password) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new Exception("User not found");
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Invalid credentials");
        }

        return user; // Return the authenticated user
    }

}
