package com.example.filrouge_back.models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class UserDTO {
    private UUID id;
    private String pseudo;
    private String mail;
    private Date birthDate;
    private String password;




}
