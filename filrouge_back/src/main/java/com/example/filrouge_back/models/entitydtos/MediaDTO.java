package com.example.filrouge_back.models.entitydtos;

import com.example.filrouge_back.entities.Evaluation;
import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.models.enums.MediaType;
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
    private Double avgRating;
    private List<Genre> genres;
    private List<Evaluation> evaluations;



}
