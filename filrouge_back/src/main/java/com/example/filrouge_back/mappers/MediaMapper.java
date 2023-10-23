package com.example.filrouge_back.mappers;


import com.example.filrouge_back.entities.Evaluation;
import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.entities.MediaProfessional;
import com.example.filrouge_back.models.entitydtos.MediaDetailDTO;
import com.example.filrouge_back.models.entitydtos.MediaSummaryDTO;
import com.example.filrouge_back.models.entitydtos.ProfessionalInfoDTO;
import com.example.filrouge_back.models.enums.JobForMedia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MediaMapper {

    private final ProfessionalMapper professionalMapper;

    public MediaSummaryDTO mediaToMediaSummaryDto(Media media) {
        if (media == null) {
            return null;
        } else {
            return MediaSummaryDTO.builder()
                    .id(media.getId())
                    .title(media.getTitle())
                    .type(media.getType())
                    .imageUrl(media.getImageUrl())
                    // TODO modifier les données BDD -> année au lieu de date
                    .releaseYear(media.getReleaseYear())
                    .duration(media.getDuration())
                    .avgRating(calculateAvgRating(media))
                    .genres(media.getGenres().stream().map(Genre::getGenreName).toList())
                    .build();
        }
    }

    public List<MediaSummaryDTO> mediaListToMediaSummaryDtoList(List<Media> mediaList) {
        if (mediaList == null) {
            return new ArrayList<>();
        } else {
            return mediaList.stream().map(this::mediaToMediaSummaryDto).toList();
        }
    }

    public MediaDetailDTO mediaToMediaDetailDto(Media media) {
        if (media == null) {
            return null;
        } else {
            return MediaDetailDTO.builder()
                    .id(media.getId())
                    .title(media.getTitle())
                    .type(media.getType())
                    .plot(media.getPlot())
                    .imageUrl(media.getImageUrl())
                    // TODO modifier les données BDD -> année au lieu de date
                    .releaseYear(media.getReleaseYear())
                    .duration(media.getDuration())
                    .seasons(media.getSeasons())
                    .inProduction(media.getInProdution())
                    .avgRating(calculateAvgRating(media))
                    .genres(media.getGenres().stream().map(Genre::getGenreName).toList())
                    .actors(getProfessionalsByJob(media.getProfessionals(), JobForMedia.ACTOR))
                    .producers(getProfessionalsByJob(media.getProfessionals(), JobForMedia.PRODUCER))
                    .writers(getProfessionalsByJob(media.getProfessionals(), JobForMedia.WRITER))
                    .directors(getProfessionalsByJob(media.getProfessionals(), JobForMedia.DIRECTOR))
                    .build();
        }
    }

    private List<ProfessionalInfoDTO> getProfessionalsByJob(List<MediaProfessional> professionals, JobForMedia job) {
        List<ProfessionalInfoDTO> professionalsByJob = new ArrayList<>();
        for (MediaProfessional professional : professionals) {
            if (professional.getJob().equals(job)) {
                professionalsByJob.add(professionalMapper.professionalToProfessionalInfoDto(
                        professional.getProfessional()));
            }
        }
        return professionalsByJob;
    }

    private Double calculateAvgRating(Media media) {
        List<Evaluation> evaluations = media.getEvaluations();
        if (evaluations != null && !evaluations.isEmpty()) {
            double sum = 0;
            for (Evaluation evaluation : evaluations) {
                if (evaluation.getRating() != null) {
                    sum += evaluation.getRating();
                }
            }
            return (sum / evaluations.size());
        } else {
            return null;
        }
    }
}
