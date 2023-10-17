package com.example.filrouge_back.controllers;


import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.models.MediaDTO;
import com.example.filrouge_back.mappers.MediaMapper;
import com.example.filrouge_back.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/media")
public class MediaRestController {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;


    @Autowired
    public MediaRestController(MediaRepository mediaRepository, MediaMapper mediaMapper, MediaMapper mediaMapper1) {
        this.mediaRepository = mediaRepository;

        this.mediaMapper = mediaMapper1;
    }




    @GetMapping("/all")
    public List<MediaDTO> getAllMedia() {
        List<Media> mediaList = mediaRepository.findAll();

        // Utilisez le mapper pour convertir les objets Media en objets MediaDTO
        List<MediaDTO> mediaDTOList = mediaList.stream()
                .map(mediaMapper::mediaToMediaDTO)
                .collect(Collectors.toList());

        return mediaDTOList;
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

    @GetMapping("/all/{genre}")
    public List<MediaDTO> getMediaByGenre(@PathVariable String genre) {
        List<Media> mediaList = mediaRepository.findByGenres_GenreName(genre);


        List<MediaDTO> mediaDTOList = mediaList.stream()
                .map(mediaMapper::mediaToMediaDTO)
                .collect(Collectors.toList());

        return mediaDTOList;
    }




}
