package com.paypal.user.service;

import com.paypal.user.entity.User;
import com.paypal.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
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
}
