package com.example.filrouge_back.models.entitydtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEditDTO {
    private UUID id;
    private String pseudo;
    private String mail;
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}
