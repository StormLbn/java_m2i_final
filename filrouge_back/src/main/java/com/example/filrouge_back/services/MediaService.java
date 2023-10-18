package com.example.filrouge_back.services;

import com.example.filrouge_back.models.entitydtos.MediaDTO;
import com.example.filrouge_back.mappers.MediaMapper;
import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.models.enums.MediaType;
import com.example.filrouge_back.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    @Autowired
    public MediaService(MediaRepository mediaRepository, MediaMapper mediaMapper) {
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
    }

    public List<MediaDTO> getAllMedia() {
        List<Media> mediaList = mediaRepository.findAll();
        return mediaMapper.mediaListToMediaDTOList(mediaList);
    }

    public Media getMediaById(UUID mediaId) {
        Optional<Media> optionalMedia = mediaRepository.findById(mediaId);
        if (optionalMedia.isPresent()) {
            Media media = optionalMedia.get();
            return media;
        }
        return null;
    }

    public List<MediaDTO> getMediaByGenre(String genre) {
        List<Media> mediaList = mediaRepository.findByGenres_GenreName(genre);
        return mediaMapper.mediaListToMediaDTOList(mediaList);
    }

    public List<MediaDTO> getMediaByType(MediaType type) {
        List<Media> mediaList = mediaRepository.findByType(type);
        return mediaMapper.mediaListToMediaDTOList(mediaList);
    }
}
