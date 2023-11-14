package com.example.filrouge_back.controllers;

import com.example.filrouge_back.models.authdtos.AuthRequest;
import com.example.filrouge_back.models.authdtos.AuthResponse;
import com.example.filrouge_back.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerHandler(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.register(authRequest));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticationHandler(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }

    @PostMapping("/sign-out")
    public void signOutHandler() {
        authService.signOut();
    }
}
