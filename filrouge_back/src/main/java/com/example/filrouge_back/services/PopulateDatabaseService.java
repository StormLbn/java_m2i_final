package com.example.filrouge_back.services;

import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.models.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.entities.MediaProfessional;
import com.example.filrouge_back.entities.Professional;
import com.example.filrouge_back.models.MediaType;
import com.example.filrouge_back.models.MovieApiResponse;
import com.example.filrouge_back.repositories.GenreRepository;
import com.example.filrouge_back.repositories.MediaProfessionalRepository;
import com.example.filrouge_back.repositories.MediaRepository;
import com.example.filrouge_back.repositories.ProfessionalRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
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
        if (mediaRepository.count() == 0) {
            log.info("Database is empty");

            List<String> idList = getTmdbIdList("movie");
            log.info("Filling with " + idList.size() + " movies data...");
            int errorsCount = 0;

            for (String id : idList) {
                try {
                    // FIXME : voir pourquoi certaines données ne s'enregistrent pas
                    getMovie(id);
                } catch (Exception e) {
                    log.warn("An error occurred while adding the movie data");
                    log.warn(e.getMessage());
                    errorsCount++;
                }
            }
            log.info((100 - errorsCount) + " movies added to DB");

            log.info("Filling with 100 shows data...");
            errorsCount = 0;
            try {
                getShows();
            } catch (Exception e) {
                log.warn("An error occurred while adding the show data");
                log.warn(e.getMessage());
                errorsCount++;
            }
            log.info((100 - errorsCount) + " shows added to DB");
        }
    }

    public void getMovie(String id) throws Exception {

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
                log.info("List size : " + showsList.size());
                int count = 0;
                for (ShowApiResponse.Show showResponse : showsList) {
                    Media show = saveShow(showResponse);
                    log.info("Show n°" + ++count);
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

    @Transactional
    public Media saveMovie(MovieApiResponse response) {
        Media movie = new Media();
        movie.setType(MediaType.MOVIE);

        movie.setBetaseriesId(response.getMovie().getId());
        movie.setTitle(response.getMovie().getOther_title().getTitle());
        movie.setPlot(response.getMovie().getSynopsis());
        movie.setImageUrl(response.getMovie().getPoster());
        movie.setReleaseDate(response.getMovie().getRelease_date());
        movie.setDuration(Math.round(response.getMovie().getLength()/60.0f));

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

        mediaRepository.save(movie);

        movie.setProfessionals(new ArrayList<>());
        for (Person data : response.getMovie().getCrew().getDirectors()) {
            Professional person = findOrSaveProfessional(data);

            MediaProfessional director = mediaProfessionalRepository.save(
                    MediaProfessional.builder()
                        .job(JobForMedia.DIRECTOR)
                        .professional(person)
                        .media(movie)
                        .build()
            );

            movie.getProfessionals().add(director);
        }

        for (Person data : response.getMovie().getCrew().getProducers()) {
            Professional person = findOrSaveProfessional(data);

            MediaProfessional producer = mediaProfessionalRepository.save(
                    MediaProfessional.builder()
                        .job(JobForMedia.PRODUCER)
                        .professional(person)
                        .media(movie)
                        .build()
            );

            movie.getProfessionals().add(producer);
        }

        // TODO refactoriser dans une méthode !
        for (Person data : response.getMovie().getCrew().getWriters()) {
            Professional person = findOrSaveProfessional(data);

            MediaProfessional writer = mediaProfessionalRepository.save(
                    MediaProfessional.builder()
                            .job(JobForMedia.WRITER)
                            .professional(person)
                            .media(movie)
                            .build()
            );

            movie.getProfessionals().add(writer);
        }

        mediaRepository.save(movie);

        return movie;
    }


    @Transactional
    public Media saveShow(ShowApiResponse.Show response) {

        Media show = new Media();
        show.setType(MediaType.SHOW);

        show.setBetaseriesId(response.getId());
        show.setTitle(response.getTitle());
        show.setPlot(response.getDescription());
        show.setSeasons(response.getSeasons());
        show.setImageUrl(response.getImages().getPoster());
        show.setReleaseDate(LocalDate.of(response.getCreation(), 1, 1));
        show.setDuration(response.getLength());
        show.setInProdution(response.getStatus().equals("Continuing"));

        show.setGenres(new ArrayList<>());
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

        show.setProfessionals(new ArrayList<>());
        for (Person data : response.getShowrunners()) {
            Professional person = findOrSaveProfessional(data);

            MediaProfessional producer = mediaProfessionalRepository.save(
                    MediaProfessional.builder()
                            .job(JobForMedia.PRODUCER)
                            .professional(person)
                            .media(show)
                            .build()
            );

            show.getProfessionals().add(producer);
        }

        mediaRepository.save(show);

        return show;
    }

    @Transactional
    public void saveActors(ActorsApiResponse response, Media media) {
        List<Person> personsList = response.getCharacters();
        if (!personsList.isEmpty()) {
            int actorsCount = Math.min(personsList.size(), 7);
            for (int i = 0; i <= actorsCount; i++) {
                Person data = personsList.get(i);
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
            mediaRepository.save(media);
        }
    }

    public Professional findOrSaveProfessional(Person data) {
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


    private List<String> getTmdbIdList(String type) {
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
