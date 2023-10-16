package com.example.filrouge_back.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String pseudo;

    private String lastName;

    private String mail;

    private String password;

    private Date birthday;

    @ManyToOne()
    @JoinColumn(name = "Role_id")
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "user_genre", // Nom de la table de liaison
            joinColumns = @JoinColumn(name = "userEntity_id"), // Clé étrangère de UserEntity
            inverseJoinColumns = @JoinColumn(name = "genre_id") // Clé étrangère de Genre
    )
    private List<Genre> genres;

}


