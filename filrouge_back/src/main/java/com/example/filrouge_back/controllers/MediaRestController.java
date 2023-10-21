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

    // Pas la méthode par défaut !
    @GetMapping("/all")
    public List<MediaSummaryDTO> getAllMedia() {
        return mediaService.getAllMedia();
    }

    // Méthode par défaut d'envoi des médias
    @GetMapping("/all/date/{page}")
    public List<MediaSummaryDTO> getMediaByReleaseDate(@PathVariable("page") int page) {
        return mediaService.getMediaByReleaseDateDescending(page);
    }

    @GetMapping("/all/genre/{genre}/{page}")
    public List<MediaSummaryDTO> getMediaByGenre(
            @PathVariable String genre,
            @PathVariable("page") int page
    ) {
        return mediaService.getMediaByGenre(genre, page);
    }

    @GetMapping("/all/type/{type}/{page}")
    public List<MediaSummaryDTO> getMediaByType(
            @PathVariable MediaType type,
            @PathVariable("page") int page
    ) {
        return mediaService.getMediaByType(type, page);
    }

    @GetMapping("/{mediaId}")
    public MediaDetailDTO getMediaById(@PathVariable UUID mediaId) {
        return mediaService.getMediaById(mediaId);
    }

}
