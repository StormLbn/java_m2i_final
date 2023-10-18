package com.example.filrouge_back.models.apidtos;

import lombok.Data;

import java.util.List;

@Data
public class ActorsApiResponse {

    private List<PersonApiResponse> characters;

}
