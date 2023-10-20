package com.example.filrouge_back.models.entitydtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class UserEditDTO {
    private UUID id;
    private String pseudo;
    private String mail;
    private String password;
    private LocalDate birthDate;
}
