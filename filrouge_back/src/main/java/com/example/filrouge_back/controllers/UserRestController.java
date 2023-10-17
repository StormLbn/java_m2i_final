package com.example.filrouge_back.controllers;

import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final UserEntityRepository userEntityRepository;

    @Autowired
    public UserRestController(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }


    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userEntityRepository.findAll();
    }

    // Route pour afficher un utilisateur par son ID
    @GetMapping("/{userId}")
    public UserEntity getUserById(@PathVariable UUID userId) {
        return userEntityRepository.findById(userId).orElse(null);
    }
}
