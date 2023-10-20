package com.example.filrouge_back.controllers;

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

}