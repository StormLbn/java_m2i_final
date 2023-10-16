package com.example.filrouge_back.services;

import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.models.MediaType;
import com.example.filrouge_back.models.MovieApiResponse;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
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

    public PopulateDatabaseService(
            @Qualifier("tmdb") RestTemplateBuilder tmdbBuilder,
            @Qualifier("betaseries") RestTemplateBuilder betaseriesBuilder
    ) {
        this.tmdbBuilder = tmdbBuilder;
        this.betaseriesBuilder = betaseriesBuilder;
    }

    @PostConstruct
    public void populateDatabase() {
        log.info("Database is empty");

        // TODO ajouter la condition "si BDD vide"
        if (true) {
            // TODO récupérer les films
            List<String> idList = getIdList("movie");

            log.info("Filling with " + idList.size() + " movies data...");
            int notFoundCount = 0;

            for (String id : idList) {
                try {
                    log.info(id);
                    getMovie(id);
                } catch (Exception e) {
                    log.warn("Movie not found on betaseries");
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
                Media movie = new Media();
                movie.setType(MediaType.MOVIE);

                movie.setBetaseriesId(response.getMovie().getId());
                movie.setTitle(response.getMovie().getOther_title().getTitle());
                movie.setPlot(response.getMovie().getSynopsis());
                movie.setImageUrl(response.getMovie().getPoster());
                movie.setReleaseDate(response.getMovie().getRelease_date());
                movie.setDuration(response.getMovie().getLength()/60);

                movie.setGenres(new ArrayList<>());
                for (String genre : response.getMovie().getGenres()) {
                    // TODO EN COURS
//                    movie.getGenres().add();
                }

                log.info(movie.toString());
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
            ResponseEntity<JsonNode> entityJson =
                    restTemplate.getForEntity(type + "/top_rated?language=fr-FR&page=" + i, JsonNode.class);

            entityJson.getBody().findPath("results").elements().forEachRemaining(e -> {
                idList.add(e.findPath("id").asText());
            });
        }

        return idList;
    }
}
