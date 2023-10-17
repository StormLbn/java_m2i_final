package com.example.filrouge_back.controllers;


import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.models.MediaDTO;
import com.example.filrouge_back.mappers.MediaMapper;
import com.example.filrouge_back.repositories.MediaRepository;
import com.example.filrouge_back.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/media")
public class MediaRestController {


    private final MediaService mediaService;

    @Autowired
    public MediaRestController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/all")
    public List<MediaDTO> getAllMedia() {
        return mediaService.getAllMedia();
    }



    @GetMapping("/{mediaId}")
    public MediaDTO getMediaById(@PathVariable UUID mediaId) {
        return mediaService.getMediaById(mediaId);
    }

    @GetMapping("/all/{genre}")
    public List<MediaDTO> getMediaByGenre(@PathVariable String genre) {
        return mediaService.getMediaByGenre(genre);
    }




}
