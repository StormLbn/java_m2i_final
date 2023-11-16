package com.example.filrouge_back.controllers;


import com.example.filrouge_back.models.entitydtos.MediaDetailDTO;
import com.example.filrouge_back.models.entitydtos.MediaSummaryDTO;
import com.example.filrouge_back.models.entitydtos.PageDTO;
import com.example.filrouge_back.models.enums.MediaType;
import com.example.filrouge_back.services.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/all/{page}")
    public ResponseEntity<PageDTO<MediaSummaryDTO>> getMediaByDefaultSorting(@PathVariable("page") int page) {
        PageDTO<MediaSummaryDTO> mediaPage = mediaService.getAllMediaByDefaultSorting(page);
        if (mediaPage.getContent().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(mediaPage);
        }
    }

    @GetMapping("/all/date/{page}")
    public ResponseEntity<PageDTO<MediaSummaryDTO>> getMediaByReleaseDate(@PathVariable("page") int page) {
        PageDTO<MediaSummaryDTO> mediaPageDTO = mediaService.getMediaByReleaseDateDescending(page);
        if (mediaPageDTO.getContent().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(mediaPageDTO);
        }
    }

    @GetMapping("/all/genre/{genre}/{page}")
    public ResponseEntity<PageDTO<MediaSummaryDTO>> getMediaByGenre(
            @PathVariable String genre,
            @PathVariable("page") int page
    ) {
        PageDTO<MediaSummaryDTO> mediaPageDTO = mediaService.getMediaByGenre(genre, page);
        if (mediaPageDTO.getContent().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(mediaPageDTO);
        }
    }

    @GetMapping("/all/type/{type}/{page}")
    public ResponseEntity<PageDTO<MediaSummaryDTO>> getMediaByType(
            @PathVariable MediaType type,
            @PathVariable("page") int page
    ) {
        PageDTO<MediaSummaryDTO> mediaPageDTO = mediaService.getMediaByType(type, page);
        if (mediaPageDTO.getContent().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(mediaPageDTO);
        }
    }

    @GetMapping("/search/{keyword}/{page}")
    public ResponseEntity<PageDTO<MediaSummaryDTO>> searchMediaByTitle(
            @PathVariable String keyword,
            @PathVariable("page") int page) {
        PageDTO<MediaSummaryDTO> mediaPageDTO = mediaService.searchMediaByTitle(keyword, page);
        if (mediaPageDTO.getContent().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(mediaPageDTO);
        }
    }

    @GetMapping("/{mediaId}")
    public MediaDetailDTO getMediaById(@PathVariable UUID mediaId) {
        return mediaService.getMediaById(mediaId);
    }

}
