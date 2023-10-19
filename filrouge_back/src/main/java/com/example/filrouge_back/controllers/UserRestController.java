package com.example.filrouge_back.controllers;

import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.models.entitydtos.UserDisplayDTO;
import com.example.filrouge_back.models.entitydtos.UserEditDTO;
import com.example.filrouge_back.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    // TODO Modification des genres préférés

    // TODO GET recommandations ?


    @GetMapping("/{userId}")
    public UserDisplayDTO getUserById(@PathVariable UUID userId) {
//        // TODO passer par le service
//        // TODO renvoyer autre chose que "null" (exception ?)

        return userService.getUserById(userId);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserEditDTO> updateUserById(@PathVariable UUID userId, @RequestBody UserEditDTO updatedUserDTO) {
        UserEditDTO updatedUserDto = userService.updateUser(userId, updatedUserDTO);
//        // TODO passer par un DTO
//        // TODO renvoyer une ResponseEntity

        if (updatedUserDto != null) {
            return ResponseEntity.ok(updatedUserDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}