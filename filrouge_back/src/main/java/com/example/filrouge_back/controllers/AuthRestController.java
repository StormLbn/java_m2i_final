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

    // TODO ajouter un endpoint pour modifier le mot de passe

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerHandler(@RequestBody AuthRequest authRequest) {
        String token = authService.register(authRequest);
        return ResponseEntity.ok(generateResponse(token));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticationHandler(@RequestBody AuthRequest authRequest) {
        String token = authService.authenticate(authRequest);

        return ResponseEntity.ok(generateResponse(token));
    }

    @PostMapping("/sign-out")
    public void signOutHandler() {
        authService.signOut();
    }

    private AuthResponse generateResponse(String token) {
        return AuthResponse.builder().token(token).build();
    }
}
