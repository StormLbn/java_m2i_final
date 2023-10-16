package com.example.filrouge_back.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ActorsApiResponse {

    private List<Person> characters;

}
