package com.example.filrouge_back.services;

import com.example.filrouge_back.entities.Evaluation;
import com.example.filrouge_back.models.entitydtos.MediaDTO;
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




    private void calculateAvgRating(Media media) {
        List<Evaluation> evaluations = media.getEvaluations();
        if (evaluations != null && !evaluations.isEmpty()) {
            int sum = 0;
            for (Evaluation evaluation : evaluations) {
                if (evaluation.getRating() != null) {
                    sum += evaluation.getRating();
                }
            }
            double avgRating = (double) sum / evaluations.size();
            media.setAvgRating(avgRating);
        } else {
            media.setAvgRating(0.0);
        }
    }


    public List<MediaDTO> getAllMedia() {
        List<Media> mediaList = mediaRepository.findAll();
        for (Media media : mediaList) {
            calculateAvgRating(media); // Calculer la moyenne des évaluations pour chaque média
        }
        return mediaMapper.mediaListToMediaDTOList(mediaList);
    }

    public Media getMediaById(UUID mediaId) {
        Optional<Media> optionalMedia = mediaRepository.findById(mediaId);
        if (optionalMedia.isPresent()) {
            Media media = optionalMedia.get();
            calculateAvgRating(media); // Calculer la moyenne des évaluations
            return media;
        } else {
            return null;
        }  }

    public List<MediaDTO> getMediaByGenre(String genre) {
        List<Media> mediaList = mediaRepository.findByGenres_GenreName(genre);
        for (Media media : mediaList) {
            calculateAvgRating(media);
        }
        return mediaMapper.mediaListToMediaDTOList(mediaList);
    }

    public List<MediaDTO> getMediaByType(MediaType type) {
        List<Media> mediaList = mediaRepository.findByType(type);
        for (Media media : mediaList) {
            calculateAvgRating(media);
        }
        return mediaMapper.mediaListToMediaDTOList(mediaList);
    }

    public List<MediaDTO> getMediaByReleaseDateDescending() {
        List<Media> mediaList = mediaRepository.findAllByOrderByReleaseDateDesc();
        for (Media media : mediaList) {
            calculateAvgRating(media);
        }
        return mediaMapper.mediaListToMediaDTOList(mediaList);
    }
}
