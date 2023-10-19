package com.example.filrouge_back.mappers;


import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.models.entitydtos.MediaSummaryDTO;

import java.util.List;

public class MediaMapper {
    public MediaSummaryDTO mediaToMediaDTO(Media media) {
        if (media == null) {
            return null;
        } else {
            return MediaSummaryDTO.builder()
                    .id(media.getId())
                    .title(media.getTitle())
                    .type(media.getType())
                    .imageUrl(media.getImageUrl())
                    // TODO modifier les données BDD -> année au lieu de date
                    .releaseYear(media.getReleaseDate().getYear())
                    .duration(media.getDuration())
                    // TODO Gérer le calcul de la note moyenne
                    .avgRating(media.getAvgRating())
                    .genres(media.getGenres().stream().map(Genre::getGenreName).toList())
                    .build();
        }
    }

    public List<MediaSummaryDTO> mediaListToMediaSummaryDTOList(List<Media> mediaList) {

        return null;
    }
}
