package com.example.filrouge_back.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    // TODO autres éléments à ajouter ?
    private String token;

}
