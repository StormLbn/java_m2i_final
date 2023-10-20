package com.example.filrouge_back.models.entitydtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenreDTO {

    private int id;
    private String genreName;

}
