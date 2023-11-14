package com.example.filrouge_back.models.entitydtos;

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
