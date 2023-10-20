package com.example.filrouge_back.models.entitydtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class GenreEditDTO {

    private UUID userId;
    private List<Integer> genreIdList;

}
