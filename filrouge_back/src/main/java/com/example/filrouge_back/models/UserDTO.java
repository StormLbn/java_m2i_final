package com.example.filrouge_back.models;

import com.example.filrouge_back.entities.Evaluation;
import com.example.filrouge_back.entities.Genre;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String pseudo;
    private String lastName;
    private String mail;
    private Date birthday;
    private List<Genre> genres;
    private List<Evaluation> evaluations;


}
