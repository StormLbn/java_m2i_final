package com.example.filrouge_back.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
// TODO AuthRequest = UserDto ?
public class AuthRequest {

    // TODO ajouter les autres données d'utilisateur
    private String mail;
    private String password;
}
