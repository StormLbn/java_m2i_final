package com.example.filrouge_back.models.entitydtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class EvaluationDTO {
    private UUID id;
    private String comment;
    private int rating;
    private UUID mediaId;
    private UUID userId;
}

