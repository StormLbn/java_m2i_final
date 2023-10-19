package com.example.filrouge_back.models.entitydtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserDisplayDTO {
    private UUID id;
    private String pseudo;
    private String mail;
    private LocalDate birthDate;
    private List<String> genres;
}
