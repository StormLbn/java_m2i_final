package com.example.filrouge_back.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequest {

    // TODO ajouter les autres données d'utilisateur
    private String mail;
    private String password;
}
