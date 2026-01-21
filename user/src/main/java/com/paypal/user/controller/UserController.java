package com.paypal.user.controller;

import com.paypal.user.entity.User;
import com.paypal.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/")

public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<User> saverUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return userService.getUserById((id)).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
