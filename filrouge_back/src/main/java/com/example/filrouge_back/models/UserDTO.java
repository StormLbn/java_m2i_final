package com.example.filrouge_back.models;

import com.example.filrouge_back.entities.Evaluation;
import com.example.filrouge_back.entities.Genre;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserDTO {
    private UUID id;
    private String pseudo;
    private String mail;
    private Date birthday;
    private String password;




}
