package com.example.filrouge_back.entities;

import com.example.filrouge_back.models.enums.MediaType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    @Column(columnDefinition = "TEXT")
    private String plot;

    private String imageUrl;

    private Integer releaseYear;

    // Duration in minutes
    private int duration;

    // For shows only
    private Integer seasons;

    // For shows only
    private Boolean inProdution;

    // TODO ajouter un cascade ?
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "media_genre",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @OneToMany(mappedBy = "media")
    private List<MediaProfessional> professionals;

    @OneToMany(mappedBy = "media")
    private List<Evaluation> evaluations;


}
