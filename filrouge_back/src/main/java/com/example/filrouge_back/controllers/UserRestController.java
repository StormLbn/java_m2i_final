package com.example.filrouge_back.controllers;

import com.example.filrouge_back.models.entitydtos.GenreEditDTO;
import com.example.filrouge_back.models.entitydtos.UserDisplayDTO;
import com.example.filrouge_back.models.entitydtos.UserEditDTO;
import com.example.filrouge_back.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public UserDisplayDTO getUserById(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserEditDTO> updateUserById(
            @PathVariable UUID userId, @RequestBody UserEditDTO userDto
    ) {
        UserEditDTO updatedUserDto = userService.updateUser(userId, userDto);

        return ResponseEntity.ok(updatedUserDto);
    }

    @PatchMapping("/{userId}/change-password")
    public ResponseEntity<String> changeUserPassword(@PathVariable UUID userId, @RequestBody UserEditDTO userDTO) {
        if (userService.changeUserPassword(userId, userDTO)) {
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @PatchMapping("/genres/{userId}")
    public ResponseEntity<UserDisplayDTO> editUserGenresList(
            @PathVariable UUID userId,
            @RequestBody GenreEditDTO newGenres
    ) {
        return ResponseEntity.ok(userService.editUserGenresList(userId, newGenres));
    }
}