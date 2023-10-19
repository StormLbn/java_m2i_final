package com.example.filrouge_back.controllers;


import com.example.filrouge_back.entities.Media;
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
    public Media getMediaById(@PathVariable UUID mediaId) {
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

}
