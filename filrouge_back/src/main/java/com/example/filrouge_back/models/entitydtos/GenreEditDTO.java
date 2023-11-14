package com.example.filrouge_back.models.entitydtos;

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
public class GenreEditDTO {

    private UUID userId;
    private List<Integer> genreIdList;

}
