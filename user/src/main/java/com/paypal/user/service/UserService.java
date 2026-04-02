package com.paypal.user.service;

import com.paypal.user.dto.LoginRequest;
import com.paypal.user.dto.SignUpRequest;
import com.paypal.user.entity.User;
import com.paypal.user.repository.UserRepository;
import com.paypal.user.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public UserService(JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.passwordEncoder =  passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public User signUp(SignUpRequest request){
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            return null;
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole("ROLE_USER");  // Normal users only!
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return user;
    }
    public User createUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public String loginUser(LoginRequest request){
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            return "User not found";
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return "Invalid credentials";
        }

        // Generate token with claims
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());

        return token;
    }
}
