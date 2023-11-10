package com.example.filrouge_back.services;

import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.mappers.MediaMapper;
import com.example.filrouge_back.models.entitydtos.MediaSummaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final MediaMapper mediaMapper;
    private final UserService userService;
    private final MediaService mediaService;

    public List<MediaSummaryDTO> getUserRecommendations(UUID userId) {
        UserEntity foundUser = userService.findUserById(userId);

        List<Genre> genreList = foundUser.getGenres();

        List<Media> mediaByGenres = mediaService.findMediaByGenresList(genreList);

        // Pour proposer des films différents à chaque fois, on mélange la liste obtenue avant de la réduire
        Collections.shuffle(mediaByGenres);

        return mediaMapper.mediaListToMediaSummaryDtoList(
                mediaByGenres.subList(0, Math.min(10, mediaByGenres.size())));
    }
}
