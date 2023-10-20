package com.example.filrouge_back.services;

import com.example.filrouge_back.exceptions.ResourceNotFoundException;
import com.example.filrouge_back.models.entitydtos.MediaDetailDTO;
import com.example.filrouge_back.models.entitydtos.MediaSummaryDTO;
import com.example.filrouge_back.mappers.MediaMapper;
import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.models.enums.MediaType;
import com.example.filrouge_back.repositories.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    public List<MediaSummaryDTO> getAllMedia() {
        List<Media> mediaList = mediaRepository.findAll();

        return mediaMapper.mediaListToMediaSummaryDtoList(mediaList);
    }

    public MediaDetailDTO getMediaById(UUID mediaId) {
        Optional<Media> optionalMedia = mediaRepository.findById(mediaId);

        if (optionalMedia.isPresent()) {
            return mediaMapper.mediaToMediaDetailDto(optionalMedia.get());
        } else {
            throw new ResourceNotFoundException("Media not found at id " + mediaId);
        }
    }

    public List<MediaSummaryDTO> getMediaByGenre(String genre) {
        List<Media> mediaList = mediaRepository.findByGenres_GenreName(genre);

        return mediaMapper.mediaListToMediaSummaryDtoList(mediaList);
    }

    public List<MediaSummaryDTO> getMediaByType(MediaType type) {
        List<Media> mediaList = mediaRepository.findByType(type);

        return mediaMapper.mediaListToMediaSummaryDtoList(mediaList);
    }

    public List<MediaSummaryDTO> getMediaByReleaseDateDescending() {
        List<Media> mediaList = mediaRepository.findAllByOrderByReleaseDateDesc();
        return mediaMapper.mediaListToMediaSummaryDtoList(mediaList);
    }

    public List<MediaSummaryDTO> getMediaByGenres(String genre1, String genre2) {
        List<MediaSummaryDTO> mediaListGenre1 = getTopMediaByGenre(genre1, 3);
        List<MediaSummaryDTO> mediaListGenre2 = getTopMediaByGenre(genre2, 3);


        mediaListGenre1.addAll(mediaListGenre2);

        return mediaListGenre1;
    }

    private List<MediaSummaryDTO> getTopMediaByGenre(String genre, int limit) {
        List<Media> mediaList = mediaRepository.findByGenres_GenreName(genre);

        return mediaMapper.mediaListToMediaSummaryDtoList(
                mediaList.stream().limit(limit).toList());
    }
}
