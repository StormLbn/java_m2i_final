package com.example.filrouge_back.models;

import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.models.MediaType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Data
@Builder
public class MediaDTO {

    private UUID id;
    private String betaseriesId;
    private String title;
    private MediaType type;
    private String imageUrl;
    private LocalDate releaseDate;
    private int duration;
    private int seasons;
    private double avgRating;
    private List<Genre> genres;

    public MediaDTO(UUID id, String betaseriesId, String title, MediaType type, String imageUrl, LocalDate releaseDate, int duration, int seasons, double avgRating, List<Genre> genres) {
        this.id = id;
        this.betaseriesId = betaseriesId;
        this.title = title;
        this.type = type;
        this.imageUrl = imageUrl;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.seasons = seasons;
        this.avgRating = avgRating;
        this.genres = genres;
    }


    public MediaDTO() {

    }
}
