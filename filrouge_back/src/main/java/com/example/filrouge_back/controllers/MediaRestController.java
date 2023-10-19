package com.example.filrouge_back.controllers;


import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.models.entitydtos.MediaDTO;
import com.example.filrouge_back.models.enums.MediaType;
import com.example.filrouge_back.services.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaRestController {

    private final MediaService mediaService;

    @GetMapping("/all")
    public List<MediaDTO> getAllMedia() {
        return mediaService.getAllMedia();
    }

    @GetMapping("/{mediaId}")
    public Media getMediaById(@PathVariable UUID mediaId) {
        return mediaService.getMediaById(mediaId);
    }

    @GetMapping("/all/genre/{genre}")
    public List<MediaDTO> getMediaByGenre(@PathVariable String genre) {
        return mediaService.getMediaByGenre(genre);
    }

    @GetMapping("/all/type/{type}")
    public List<MediaDTO> getMediaByType(@PathVariable MediaType type) {
        return mediaService.getMediaByType(type);
    }

    @GetMapping("/all/date")
    public List<MediaDTO> getMediaByReleaseDate() {
        return mediaService.getMediaByReleaseDateDescending();
    }
    @GetMapping("/all/genres")
    public List<MediaDTO> getMediaByGenres(
            @RequestParam("genre1") String genre1,
            @RequestParam("genre2") String genre2) {
        return mediaService.getMediaByGenres(genre1, genre2);
    }

}
