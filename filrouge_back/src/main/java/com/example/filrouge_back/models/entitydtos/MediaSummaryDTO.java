package com.example.filrouge_back.models.entitydtos;

import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.models.enums.MediaType;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;


@Data
@Builder
public class MediaSummaryDTO {

    private UUID id;
    private String title;
    private MediaType type;
    private String imageUrl;
    private Integer releaseYear;
    private Integer duration;
    private Double avgRating;
    private List<String> genres;

}
