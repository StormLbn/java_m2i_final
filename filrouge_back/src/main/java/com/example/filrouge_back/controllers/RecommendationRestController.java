package com.example.filrouge_back.controllers;

import com.example.filrouge_back.models.entitydtos.MediaSummaryDTO;
import com.example.filrouge_back.services.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationRestController {

    private final RecommendationService service;

    @GetMapping("{userId}")
    public List<MediaSummaryDTO> getRecommendationsForUser(@PathVariable UUID userId) {
        return service.getUserRecommendations(userId);
    }
}
