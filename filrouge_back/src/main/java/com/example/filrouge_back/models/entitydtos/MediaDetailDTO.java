package com.example.filrouge_back.models.entitydtos;

import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.models.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaDetailDTO {

    private UUID id;
    private String title;
    private MediaType type;
    private String plot;
    private String imageUrl;
    private Integer releaseYear;
    private int duration;
    private Integer seasons;
    private Boolean inProduction;
    private Double avgRating;
    private List<String> genres;
    private List<ProfessionalInfoDTO> actors;
    private List<ProfessionalInfoDTO> producers;
    private List<ProfessionalInfoDTO> writers;
    private List<ProfessionalInfoDTO> directors;

}
