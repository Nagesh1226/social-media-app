package com.example.social_media_app.controller;

import com.example.social_media_app.entity.UserEntity;
import com.example.social_media_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.createUser(user);
    }
}
