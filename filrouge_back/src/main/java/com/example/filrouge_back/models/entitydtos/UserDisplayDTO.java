package com.example.filrouge_back.models.entitydtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDisplayDTO {
    private UUID id;
    private String pseudo;
    private String mail;
    private LocalDate birthDate;
    private List<String> genres;
}
