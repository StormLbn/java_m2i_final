package com.example.filrouge_back.entities;

import com.example.filrouge_back.models.MediaType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Transient
    private String betaseriesId;

    private String title;

    private MediaType type;

    private String plot;

    private String imageUrl;

    private LocalDate releaseDate;

    // Duration in minutes
    private int duration;

    // For shows only
    private int seasons;

    // For shows only
    private boolean inProdution;

    @Transient
    private double avgRating;

    // TODO ajouter un cascade ?
    @ManyToMany
    @JoinTable(
            name = "media_genre",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @OneToMany(mappedBy = "media")
    private List<MediaProfessional> professionals;
}
