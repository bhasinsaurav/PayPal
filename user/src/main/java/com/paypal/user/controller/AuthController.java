package com.paypal.user.controller;

import com.paypal.user.dto.JwtResponse;
import com.paypal.user.dto.LoginRequest;
import com.paypal.user.dto.SignUpRequest;
import com.paypal.user.entity.User;
import com.paypal.user.repository.UserRepository;
import com.paypal.user.service.UserService;
import com.paypal.user.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;



    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService =  userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request){
        User user = userService.signUp(request);
        if(user==null){
            return ResponseEntity.badRequest().body("User already exists");
        }

        return ResponseEntity.ok("User registered successfully");
    }





    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        String token = userService.loginUser(request);

        if(token.equals("User not found") || token.equals("Invalid credentials")){
            return ResponseEntity.status(401).body(token);
        }
        return ResponseEntity.ok(new JwtResponse(token));
    }

}
