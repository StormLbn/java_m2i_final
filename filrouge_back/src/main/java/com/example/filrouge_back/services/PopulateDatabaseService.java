package com.example.filrouge_back.services;

import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.models.MediaType;
import com.example.filrouge_back.models.MovieApiResponse;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.entities.MediaProfessional;
import com.example.filrouge_back.entities.Professional;
import com.example.filrouge_back.models.JobForMedia;
import com.example.filrouge_back.models.MediaType;
import com.example.filrouge_back.models.MovieApiResponse;
import com.example.filrouge_back.repositories.GenreRepository;
import com.example.filrouge_back.repositories.MediaProfessionalRepository;
import com.example.filrouge_back.repositories.MediaRepository;
import com.example.filrouge_back.repositories.ProfessionalRepository;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PopulateDatabaseService {

    private final RestTemplateBuilder tmdbBuilder;
    private final RestTemplateBuilder betaseriesBuilder;
    private final MediaRepository mediaRepository;
    private final MediaProfessionalRepository mediaProfessionalRepository;
    private final ProfessionalRepository professionalRepository;
    private final GenreRepository genreRepository;

    public PopulateDatabaseService(
            @Qualifier("tmdb") RestTemplateBuilder tmdbBuilder,
            @Qualifier("betaseries") RestTemplateBuilder betaseriesBuilder,
            MediaRepository mediaRepository,
            MediaProfessionalRepository mediaProfessionalRepository,
            ProfessionalRepository professionalRepository,
            GenreRepository genreRepository
    ) {
        this.tmdbBuilder = tmdbBuilder;
        this.betaseriesBuilder = betaseriesBuilder;
        this.mediaRepository = mediaRepository;
        this.mediaProfessionalRepository = mediaProfessionalRepository;
        this.professionalRepository = professionalRepository;
        this.genreRepository = genreRepository;
    }

    @PostConstruct
    public void populateDatabase() {
//        if (mediaRepository.count() == 0) {
        if (true) {
            log.info("Database is empty");

            List<String> idList = getIdList("movie");

            log.info("Filling with " + idList.size() + " movies data...");
            int notFoundCount = 0;

            for (String id : idList) {
                try {
                    log.info(id);
                    getMovie(id);
                } catch (Exception e) {
                    log.warn("Movie could not be added from betaseries");
                    notFoundCount++;
                }
            }
            log.info((100 - notFoundCount) + " movies added to DB");

            // TODO récupérer les séries
//            getShows();
        }
    }

    public void getMovie(String id) throws Exception {

        RestTemplate restTemplate = betaseriesBuilder.build();

        try {
            MovieApiResponse response =
                    restTemplate.getForEntity("movies/movie?tmdb_id=" + id, MovieApiResponse.class).getBody();

            if (response != null) {
                Media movie = saveMovie(response);

            }


//            response = restTemplate.getForEntity("movies/characters?id=" + id, JsonNode.class);
//            response.getBody().findPath("results").elements().forEachRemaining(e -> {
//                // TODO récupérer les données intéressantes des acteurs
//            });
        } catch (HttpClientErrorException e) {
            throw new Exception("Movie with id " + id + " nout found", e);
        }
        // TODO sauvegarder OU retourner les films & leurs acteurs
    }

    @Transactional
    public Media saveMovie(MovieApiResponse response) {
        Media movie = new Media();
        movie.setType(MediaType.MOVIE);

        movie.setBetaseriesId(response.getMovie().getId());
        movie.setTitle(response.getMovie().getOther_title().getTitle());
        movie.setPlot(response.getMovie().getSynopsis());
        movie.setImageUrl(response.getMovie().getPoster());
        movie.setReleaseDate(response.getMovie().getRelease_date());
        movie.setDuration(response.getMovie().getLength()/60);

        movie.setGenres(new ArrayList<>());
        for (String data : response.getMovie().getGenres()) {
            Genre genre;
            if (genreRepository.existsByGenreName(data)) {
                genre = genreRepository.findByGenreName(data);
            } else {
                genre = genreRepository.save(
                        Genre.builder().genreName(data).build()
                );
            }

            movie.getGenres().add(genre);
        }

        movie.setProfessionals(new ArrayList<>());
        for (MovieApiResponse.Movie.Crew.Person data : response.getMovie().getCrew().getDirectors()) {
            Professional person = findOrSaveProfessional(data);

            MediaProfessional director = mediaProfessionalRepository.save(
                    MediaProfessional.builder()
                    .job(JobForMedia.DIRECTOR)
                    .professional(person)
                    .build()
            );

            movie.getProfessionals().add(director);
        }

        for (MovieApiResponse.Movie.Crew.Person data : response.getMovie().getCrew().getProducers()) {
            Professional person = findOrSaveProfessional(data);

            MediaProfessional director = mediaProfessionalRepository.save(
                    MediaProfessional.builder()
                    .job(JobForMedia.PRODUCER)
                    .professional(person)
                    .build()
            );

            movie.getProfessionals().add(director);
        }


        for (MovieApiResponse.Movie.Crew.Person data : response.getMovie().getCrew().getWriters()) {
            Professional person = findOrSaveProfessional(data);

            MediaProfessional director = mediaProfessionalRepository.save(
                    MediaProfessional.builder()
                            .job(JobForMedia.WRITER)
                            .professional(person)
                            .build()
            );

            movie.getProfessionals().add(director);
        }

        mediaRepository.save(movie);

        return movie;
    }

    public Professional findOrSaveProfessional(MovieApiResponse.Movie.Crew.Person data) {
        Professional person;

        if (professionalRepository.existsByName(data.getName())) {
            person = professionalRepository.findByName(data.getName());
        } else {
            person = professionalRepository.save(
                    Professional.builder()
                            .name(data.getName())
                            .imageUrl(data.getPicture())
                            .build()
            );
        }

        return person;
    }

    public void getShows() {
        log.info("Filling with shows data");

        RestTemplate restTemplate = betaseriesBuilder.build();

        List<String> idList = new ArrayList<>();

        ResponseEntity<JsonNode> entityJson =
                restTemplate.getForEntity("shows/list?limit=100&order=popularity", JsonNode.class);

        entityJson.getBody().findPath("results").elements().forEachRemaining(e -> {
            idList.add(e.findPath("id").asText());
            // TODO récupérer les données intéressantes des séries
        });



        // TODO Récupérer les données betaseries
        for (String id : idList) {

            entityJson = restTemplate.getForEntity("movies/characters?id=" + id, JsonNode.class);
            entityJson.getBody().findPath("results").elements().forEachRemaining(e -> {
                // TODO récupérer les données intéressantes des acteurs
            });

            // TODO sauvegarder OU retourner les films & leurs acteurs
        }
    }


    private List<String> getIdList(String type) {
        RestTemplate restTemplate = tmdbBuilder.build();

        List<String> idList = new ArrayList<>();

        for (int i = 1 ; i <= 5 ; i++) {
            JsonNode entityJson =
                    restTemplate.getForEntity(type + "/top_rated?language=fr-FR&page=" + i, JsonNode.class).getBody();

            if (entityJson != null) {
                entityJson.findPath("results").elements()
                        .forEachRemaining(e -> idList.add(e.findPath("id").asText()));
            }
        }

        return idList;
    }
}
