package com.example.filrouge_back.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String genreName;

    @ManyToMany(mappedBy = "genres")
    private List<UserEntity> users;

    @ManyToMany(mappedBy = "genres")
    private Collection<Media> medias;

}
