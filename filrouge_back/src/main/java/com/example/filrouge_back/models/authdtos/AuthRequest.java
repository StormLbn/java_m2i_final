package com.example.filrouge_back.models.authdtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
// TODO AuthRequest = UserDto ?
public class AuthRequest {

    // TODO ajouter les autres données d'utilisateur
    private String mail;
    private String password;
    private String pseudo;
    private LocalDate birthDate;

    // TODO à faire à l'inscription ?
//    private List<String> genres;
}
