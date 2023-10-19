package com.example.filrouge_back.controllers;

import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.models.entitydtos.UserDTO;
import com.example.filrouge_back.repositories.UserEntityRepository;
import com.example.filrouge_back.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserEntityRepository userEntityRepository;
    private final UserService userService;

    // TODO Modification des genres préférés

    @GetMapping("/{userId}")
    public UserEntity getUserById(@PathVariable UUID userId) {
        // TODO passer par le service
        // TODO renvoyer autre chose que "null" (exception ?)
        return userEntityRepository.findById(userId).orElse(null);
    }

    @PatchMapping("/{userId}")
    public UserEntity updateUserById(@PathVariable UUID userId, @RequestBody UserDTO updatedUserDTO) {
        // TODO passer par un DTO
        // TODO renvoyer une ResponseEntity
        return userService.updateUser(userId, updatedUserDTO);
    }

}