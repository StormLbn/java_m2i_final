package com.example.filrouge_back.services;

import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.mappers.MediaMapper;
import com.example.filrouge_back.models.apidtos.ActorsApiResponse;
import com.example.filrouge_back.models.apidtos.PersonApiResponse;
import com.example.filrouge_back.models.apidtos.ShowApiResponse;
import com.example.filrouge_back.models.enums.JobForMedia;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.entities.MediaProfessional;
import com.example.filrouge_back.entities.Professional;
import com.example.filrouge_back.models.enums.MediaType;
import com.example.filrouge_back.models.apidtos.MovieApiResponse;
import com.example.filrouge_back.repositories.GenreRepository;
import com.example.filrouge_back.repositories.MediaProfessionalRepository;
import com.example.filrouge_back.repositories.MediaRepository;
import com.example.filrouge_back.repositories.ProfessionalRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
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

    // TODO refactoriser pour utiliser les services au lieu des repos !
    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;
    private final GenreRepository genreRepository;

    private final ProfessionalService professionalService;

    public PopulateDatabaseService(
            @Qualifier("tmdb") RestTemplateBuilder tmdbBuilder,
            @Qualifier("betaseries") RestTemplateBuilder betaseriesBuilder,
            MediaRepository mediaRepository,
            MediaMapper mediaMapper,
            GenreRepository genreRepository,
            ProfessionalService professionalService
    ) {
        this.tmdbBuilder = tmdbBuilder;
        this.betaseriesBuilder = betaseriesBuilder;
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
        this.genreRepository = genreRepository;
        this.professionalService = professionalService;
    }

    @PostConstruct
    public void populateDatabase() {
        // TODO méthode dans MediaService
        if (mediaRepository.count() == 0) {
            log.info("Media database is empty");

            List<String> idList = getTmdbIdList();
            log.info("Filling with " + idList.size() + " movies data...");
            List<String> failedIds = new ArrayList<>();

            for (String id : idList) {
                try {
                    getMovies(id);
                } catch (Exception e) {
                    log.warn("An error occurred while adding the movie data at ID " + id + " :");
                    log.warn(e.getMessage());
                    failedIds.add(id);
                }
            }
            log.info((100 - failedIds.size()) + " movies added to DB");
            log.info("Failed to add movies at IDs : " + failedIds);

            try {
                getShows();
            } catch (Exception e) {
                log.warn("An error occurred while adding the show data");
                log.warn(e.getMessage());
            }
            log.info("Shows added to DB");

            log.info("Finished adding media to database");
        }
    }

    public void getMovies(String id) throws Exception {

        RestTemplate restTemplate = betaseriesBuilder.build();
        Media movie;

        try {
            MovieApiResponse moviesResponse = restTemplate
                    .getForEntity("movies/movie?tmdb_id=" + id, MovieApiResponse.class).getBody();

            if (moviesResponse != null) {
                movie = saveMovie(moviesResponse);

                ActorsApiResponse actorsResponse = restTemplate
                        .getForEntity("movies/characters?id=" + movie.getBetaseriesId(), ActorsApiResponse.class).getBody();

                if (actorsResponse != null) {
                    saveActors(actorsResponse, movie);
                }
            }

        } catch (HttpClientErrorException e) {
            log.warn("Movie with id " + id + " not found on betaseries");
            throw new Exception(e);
        }
    }

    public void getShows() throws Exception {

        RestTemplate restTemplate = betaseriesBuilder.build();

        try {
            ShowApiResponse showsResponse = restTemplate.getForEntity("shows/list?limit=100&order=popularity", ShowApiResponse.class).getBody();

            if (showsResponse != null) {

                List<ShowApiResponse.Show> showsList = showsResponse.getShows();
                log.info("Filling with " + showsList.size() + " shows data...");
                for (ShowApiResponse.Show showResponse : showsList) {
                    Media show = saveShow(showResponse);

                    ActorsApiResponse actorsResponse = restTemplate
                            .getForEntity("shows/characters?id=" + showResponse.getId(), ActorsApiResponse.class).getBody();

                    if (actorsResponse != null) {
                        saveActors(actorsResponse, show);
                    }
                }
            }

        } catch (HttpClientErrorException e) {
            log.warn("Show not found");
            throw new Exception(e);
        }
    }

    // TODO déplacer dans MediaService
    @Transactional
    public Media saveMovie(MovieApiResponse response) {
        Media movie = mediaMapper.movieApiResponseToMedia(response);

        // TODO méthode dans GenreService
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

        mediaRepository.save(movie);

        professionalService.saveMediaProfessionals(response.getMovie().getCrew().getDirectors(), movie, JobForMedia.DIRECTOR);
        professionalService.saveMediaProfessionals(response.getMovie().getCrew().getProducers(), movie, JobForMedia.PRODUCER);
        professionalService.saveMediaProfessionals(response.getMovie().getCrew().getWriters(), movie, JobForMedia.WRITER);

        mediaRepository.save(movie);

        return movie;
    }

    // TODO déplacer dans MediaService
    @Transactional
    public Media saveShow(ShowApiResponse.Show response) {
        Media show = mediaMapper.showApiResponseShowToMedia(response);

        // TODO méthode dans GenreService
        for (String data : response.getGenres().values()) {
            Genre genre;
            if (genreRepository.existsByGenreName(data)) {
                genre = genreRepository.findByGenreName(data);
            } else {
                genre = genreRepository.save(
                        Genre.builder().genreName(data).build()
                );
            }

            show.getGenres().add(genre);
        }

        mediaRepository.save(show);

        professionalService.saveMediaProfessionals(response.getShowrunners(), show, JobForMedia.PRODUCER);

        mediaRepository.save(show);

        return show;
    }

    public void saveActors(ActorsApiResponse response, Media media) {
        List<PersonApiResponse> personsList = response.getCharacters();
        if (!personsList.isEmpty()) {
            professionalService.saveActors(personsList, media);
            mediaRepository.save(media);
        }
    }

    private List<String> getTmdbIdList() {
        RestTemplate restTemplate = tmdbBuilder.build();

        List<String> idList = new ArrayList<>();

        for (int i = 1 ; i <= 5 ; i++) {
            JsonNode entityJson =
                    restTemplate.getForEntity("movie/top_rated?language=fr-FR&page=" + i, JsonNode.class).getBody();

            if (entityJson != null) {
                entityJson.findPath("results").elements()
                        .forEachRemaining(e -> idList.add(e.findPath("id").asText()));
            }
        }

        return idList;
    }
}