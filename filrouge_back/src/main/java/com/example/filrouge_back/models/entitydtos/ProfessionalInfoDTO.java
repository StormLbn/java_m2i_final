package com.example.filrouge_back.models.entitydtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProfessionalInfoDTO {
    private UUID id;
    private String name;
    private String imageUrl;
}
