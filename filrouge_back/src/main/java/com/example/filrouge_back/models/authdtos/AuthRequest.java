package com.example.filrouge_back.models.authdtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AuthRequest {

    private String mail;
    private String password;
    private String pseudo;
    private LocalDate birthDate;

}
