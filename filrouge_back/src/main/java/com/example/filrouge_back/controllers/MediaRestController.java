package com.example.filrouge_back.controllers;


import com.example.filrouge_back.models.entitydtos.MediaDetailDTO;
import com.example.filrouge_back.models.entitydtos.MediaSummaryDTO;
import com.example.filrouge_back.models.enums.MediaType;
import com.example.filrouge_back.services.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<MediaSummaryDTO>> getAllMedia() {
        List<MediaSummaryDTO> mediaList = mediaService.getAllMedia();
        if (mediaList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(mediaList);
        }
    }

    // Méthode par défaut d'envoi des médias
    // TODO modifier pour trier par IDs pour avoir plus d'aléatoire (sinon que des séries)
    @GetMapping("/all/date/{page}")
    public ResponseEntity<List<MediaSummaryDTO>> getMediaByReleaseDate(@PathVariable("page") int page) {
        List<MediaSummaryDTO> mediaList = mediaService.getMediaByReleaseDateDescending(page);
        if (mediaList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(mediaList);
        }
    }

    @GetMapping("/all/genre/{genre}/{page}")
    public ResponseEntity<List<MediaSummaryDTO>> getMediaByGenre(
            @PathVariable String genre,
            @PathVariable("page") int page
    ) {
        List<MediaSummaryDTO> mediaList = mediaService.getMediaByGenre(genre, page);
        if (mediaList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(mediaList);
        }
    }

    @GetMapping("/all/type/{type}/{page}")
    public ResponseEntity<List<MediaSummaryDTO>> getMediaByType(
            @PathVariable MediaType type,
            @PathVariable("page") int page
    ) {
        List<MediaSummaryDTO> mediaList = mediaService.getMediaByType(type, page);
        if (mediaList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(mediaList);
        }
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<MediaSummaryDTO>> searchMediaByTitle(@PathVariable String keyword) {
        List<MediaSummaryDTO> mediaList = mediaService.searchMediaByTitle(keyword);
        if (mediaList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(mediaList);
        }
    }

    @GetMapping("/{mediaId}")
    public MediaDetailDTO getMediaById(@PathVariable UUID mediaId) {
        return mediaService.getMediaById(mediaId);
    }

}
