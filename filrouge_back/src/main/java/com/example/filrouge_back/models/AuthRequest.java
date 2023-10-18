package com.example.filrouge_back.models;

import com.example.filrouge_back.entities.Genre;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
// TODO AuthRequest = UserDto ?
public class AuthRequest {

    // TODO ajouter les autres données d'utilisateur
    private String mail;
    private String password;
    private String pseudo;
    private Date birthDate;

    // TODO à faire à l'inscription ?
//    private List<String> genres;
}
