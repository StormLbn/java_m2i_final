package com.example.filrouge_back.services;

import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.entities.MediaProfessional;
import com.example.filrouge_back.entities.Professional;
import com.example.filrouge_back.models.apidtos.PersonApiResponse;
import com.example.filrouge_back.models.enums.JobForMedia;
import com.example.filrouge_back.repositories.MediaProfessionalRepository;
import com.example.filrouge_back.repositories.ProfessionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessionalService {

    private final ProfessionalRepository professionalRepository;
    private final MediaProfessionalRepository mediaProfessionalRepository;

    public Professional findOrSaveProfessional(PersonApiResponse data) {
        Professional person;
        String name = data.getActor() != null
                ? data.getActor()
                : data.getName();

        if (professionalRepository.existsByName(name)) {
            person = professionalRepository.findByName(name);
        } else {
            person = professionalRepository.save(
                    Professional.builder()
                            .name(name)
                            .imageUrl(data.getPicture())
                            .build()
            );
        }

        return person;
    }

    public void saveMediaProfessionals(List<PersonApiResponse> persons, Media movie, JobForMedia job) {
        for (PersonApiResponse data : persons) {
            Professional person = findOrSaveProfessional(data);

            MediaProfessional professional = mediaProfessionalRepository.save(
                    MediaProfessional.builder()
                            .job(job)
                            .professional(person)
                            .media(movie)
                            .build()
            );

            movie.getProfessionals().add(professional);
        }
    }

    public void saveActors(List<PersonApiResponse> response, Media media) {
        int actorsCount = Math.min(response.size(), 7);
        for (int i = 0; i < actorsCount; i++) {
            PersonApiResponse data = response.get(i);
            Professional person = findOrSaveProfessional(data);

            MediaProfessional actor = mediaProfessionalRepository.save(
                    MediaProfessional.builder()
                            .job(JobForMedia.ACTOR)
                            .professional(person)
                            .media(media)
                            .build()
            );

            media.getProfessionals().add(actor);
        }
    }
}
