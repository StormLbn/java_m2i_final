package com.example.filrouge_back.controllers;

import com.example.filrouge_back.models.AuthRequest;
import com.example.filrouge_back.models.AuthResponse;
import com.example.filrouge_back.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerHandler(@RequestBody AuthRequest authRequest) {
        authService.register(authRequest);

        return authenticationHandler(authRequest);
    }


    @PostMapping("authenticate")
    public ResponseEntity<AuthResponse> authenticationHandler(AuthRequest authRequest) {
        String token = authService.authenticate(authRequest);

        AuthResponse response = AuthResponse.builder()
                .token(token)
                .build();

        return ResponseEntity.ok(response);
    }
}
