package com.example.filrouge_back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    private List<UserEntity> users;

    @Column(unique = true)
    private String genreName;

    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    private Collection<Media> medias;

}
