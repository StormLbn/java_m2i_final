package com.example.filrouge_back.controllers;


import com.example.filrouge_back.models.entitydtos.MediaDetailDTO;
import com.example.filrouge_back.models.entitydtos.MediaSummaryDTO;
import com.example.filrouge_back.models.enums.MediaType;
import com.example.filrouge_back.services.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaRestController {

    private final MediaService mediaService;

    @GetMapping("/all")
    public List<MediaSummaryDTO> getAllMedia() {
        return mediaService.getAllMedia();
    }

    @GetMapping("/{mediaId}")
    public MediaDetailDTO getMediaById(@PathVariable UUID mediaId) {
        return mediaService.getMediaById(mediaId);
    }

    @GetMapping("/all/genre/{genre}")
    public List<MediaSummaryDTO> getMediaByGenre(@PathVariable String genre) {
        return mediaService.getMediaByGenre(genre);
    }

    @GetMapping("/all/type/{type}")
    public List<MediaSummaryDTO> getMediaByType(@PathVariable MediaType type) {
        return mediaService.getMediaByType(type);
    }

    @GetMapping("/all/date")
    public List<MediaSummaryDTO> getMediaByReleaseDate() {
        return mediaService.getMediaByReleaseDateDescending();
    }
    @GetMapping("/all/genres")
    public List<MediaSummaryDTO> getMediaRecommendationsByGenres(
            @RequestParam("genre1") String genre1,
            @RequestParam("genre2") String genre2) {
        return mediaService.getRecommendationsByGenres(genre1, genre2);
    }
//  il faut ecrire comme ca dans l'url :   http://localhost:8080/api/media/all/genres?genre1=Drame&genre2=Action

}
