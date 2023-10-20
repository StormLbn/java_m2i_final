package com.example.filrouge_back.models.authdtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private String token;

}
