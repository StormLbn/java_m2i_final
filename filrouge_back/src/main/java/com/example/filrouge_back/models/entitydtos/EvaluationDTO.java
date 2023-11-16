package com.example.filrouge_back.models.entitydtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationDTO {
    private UUID id;
    private String comment;
    private Integer rating;
    private UUID mediaId;
    private String mediaTitle;
    private UUID userId;
    private String userName;
}

