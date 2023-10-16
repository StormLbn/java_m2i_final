package com.example.filrouge_back.controllers;


import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/media")
public class MediaRestController {

    private final MediaRepository mediaRepository;

    @Autowired
    public MediaRestController(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @GetMapping("/all")
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    @GetMapping("/{mediaId}") // Spécifiez l'ID comme variable de chemin
    public Media getMediaById(@PathVariable UUID mediaId) {
        Optional<Media> optionalMedia = mediaRepository.findById(mediaId);
        if (optionalMedia.isPresent()) {
            return optionalMedia.get();
        } else {
            // Gérer le cas où le média n'a pas été trouvé, par exemple, en lançant une exception.
            // Vous pouvez également retourner une réponse HTTP 404 (Not Found), selon vos besoins.
            return null;
        }
    }
}
