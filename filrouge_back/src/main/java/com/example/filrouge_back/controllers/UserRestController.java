package com.example.filrouge_back.controllers;

import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.models.entitydtos.UserDTO;
import com.example.filrouge_back.repositories.UserEntityRepository;
import com.example.filrouge_back.services.UserService;
import com.example.filrouge_back.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserEntityRepository userEntityRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    // TODO Modification des genres préférés

    // TODO GET recommandations ?


    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable UUID userId) {
//        // TODO passer par le service
//        // TODO renvoyer autre chose que "null" (exception ?)

        UserEntity user = userService.getUserById(userId);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable UUID userId, @RequestBody UserDTO updatedUserDTO) {
        UserDTO updatedUserDto = userService.updateUser(userId, updatedUserDTO);
//        // TODO passer par un DTO
//        // TODO renvoyer une ResponseEntity

        if (updatedUserDto != null) {
            return ResponseEntity.ok(updatedUserDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}