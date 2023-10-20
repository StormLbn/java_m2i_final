package com.example.filrouge_back.services;

import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.exceptions.ResourceNotFoundException;
import com.example.filrouge_back.mappers.MediaMapper;
import com.example.filrouge_back.models.entitydtos.MediaSummaryDTO;
import com.example.filrouge_back.repositories.MediaRepository;
import com.example.filrouge_back.repositories.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    // TODO recactor : doit utiliser les autres services !!!
    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;
    private final UserEntityRepository userEntityRepository;


    public List<MediaSummaryDTO> getUserRecommendations(UUID userId) {
        Optional<UserEntity> foundUser = userEntityRepository.findById(userId);

        if (foundUser.isPresent()) {
            List<Genre> genreList = foundUser.get().getGenres();

            List<Media> mediaByGenres = new ArrayList<>(mediaRepository.findByGenresList(genreList).stream().toList());

            Collections.shuffle(mediaByGenres);

            return mediaMapper.mediaListToMediaSummaryDtoList(
                    mediaByGenres.subList(0, Math.min(10, mediaByGenres.size())));
        } else {
            throw new ResourceNotFoundException("User not found at id " + userId);
        }
    }
}
