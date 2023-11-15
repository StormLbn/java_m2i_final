package com.example.filrouge_back.models.authdtos;

import com.example.filrouge_back.models.entitydtos.UserDisplayDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String token;
    private UserDisplayDTO user;

}
