package com.example.filrouge_back.controllers;

import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.models.entitydtos.UserDTO;
import com.example.filrouge_back.repositories.UserEntityRepository;
import com.example.filrouge_back.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final UserEntityRepository userEntityRepository;
    private final UserService userService;

    @Autowired
    public UserRestController(UserEntityRepository userEntityRepository, UserService userService) {
        this.userEntityRepository = userEntityRepository;
        this.userService = userService;
    }


    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userEntityRepository.findAll();
    }


    @GetMapping("/{userId}")
    public UserEntity getUserById(@PathVariable UUID userId) {
        return userEntityRepository.findById(userId).orElse(null);
    }
    @PatchMapping("/edit/{userId}")
    public UserEntity updateUserById(@PathVariable UUID userId, @RequestBody UserDTO updatedUserDTO) {
        return userService.updateUser(userId, updatedUserDTO);
    }

}