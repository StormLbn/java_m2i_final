package com.example.filrouge_back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String comment;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "media_id")
    @JsonIgnore
    private Media media;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
//    @Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "media_id"}))
    private UserEntity user;



}
