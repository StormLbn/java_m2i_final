package com.example.filrouge_back.controllers;


import com.example.filrouge_back.entities.Media;
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



    @Autowired
    public MediaRestController(MediaRepository mediaRepository, MediaMapper mediaMapper) {
        this.mediaRepository = mediaRepository;

    }

//    @GetMapping("/all")
//    public List<Media> getAllMedia() {
//        return mediaRepository.findAll();
//    }

    private com.example.filrouge_back.dto.MediaDTO convertToDTO(Media media) {
        com.example.filrouge_back.dto.MediaDTO mediaDTO = new com.example.filrouge_back.dto.MediaDTO();
        mediaDTO.setId(media.getId());
        mediaDTO.setBetaseriesId(media.getBetaseriesId());
        mediaDTO.setTitle(media.getTitle());
        mediaDTO.setType(media.getType());
        mediaDTO.setImageUrl(media.getImageUrl());
        mediaDTO.setReleaseDate(media.getReleaseDate());
        mediaDTO.setDuration(media.getDuration());
        mediaDTO.setSeasons(media.getSeasons());
        mediaDTO.setAvgRating(media.getAvgRating());
        mediaDTO.setGenres(media.getGenres());
        return mediaDTO;
    }




    @GetMapping("/all")
    public List<com.example.filrouge_back.dto.MediaDTO> getAllMedia() {
        List<Media> mediaList = mediaRepository.findAll();

        // Transformez les objets Media en objets MediaDTO
        List<com.example.filrouge_back.dto.MediaDTO> mediaDTOList = mediaList.stream()
                .map(this::convertToDTO)
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
    public List<com.example.filrouge_back.dto.MediaDTO> getMediaByGenre(@PathVariable String genre) {
        List<Media> mediaList = mediaRepository.findByGenres_GenreName(genre);


        List<com.example.filrouge_back.dto.MediaDTO> mediaDTOList = mediaList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return mediaDTOList;
    }




}
