package com.example.filrouge_back.services;

import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.exceptions.ApiClientErrorException;
import com.example.filrouge_back.models.apidtos.ActorsApiResponse;
import com.example.filrouge_back.models.apidtos.ShowApiResponse;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import com.example.filrouge_back.models.apidtos.MovieApiResponse;
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
    private final MediaService mediaService;

    public PopulateDatabaseService(
            @Qualifier("tmdb") RestTemplateBuilder tmdbBuilder,
            @Qualifier("betaseries") RestTemplateBuilder betaseriesBuilder,
            MediaService mediaService
    ) {
        this.tmdbBuilder = tmdbBuilder;
        this.betaseriesBuilder = betaseriesBuilder;
        this.mediaService = mediaService;
    }

    @PostConstruct
    public void populateDatabase() {
        if (mediaService.isTableEmpty()) {
            log.info("Media database is empty");
            log.info("Fetching data from APIs : this could take a few minutes...");

            List<String> idList = getTmdbIdList();
            log.info("Filling with " + idList.size() + " movies data...");
            List<String> failedIds = new ArrayList<>();

            for (String id : idList) {
                try {
                    getMovieFromApi(id);
                } catch (Exception e) {
                    log.warn("An error occurred while adding the movie data at ID " + id + " :");
                    log.warn(e.getMessage());
                    failedIds.add(id);
                }
            }
            log.info((idList.size() - failedIds.size()) + " movies added to DB");
            if (!failedIds.isEmpty()) {
                log.info("Failed to add movies at IDs : " + failedIds);
            }

            try {
                getShowsFromApi();
            } catch (Exception e) {
                log.warn("An error occurred while adding the show data :");
                log.warn(e.getMessage());
            }
            log.info("Shows added to DB");

            log.info("Finished adding media to database");
        }
    }

    public void getMovieFromApi(String id) throws ApiClientErrorException {

        RestTemplate restTemplate = betaseriesBuilder.build();
        Media movie;

        try {
            MovieApiResponse movieResponse = restTemplate
                    .getForEntity("movies/movie?tmdb_id=" + id, MovieApiResponse.class).getBody();

            if (movieResponse != null) {
                movie = mediaService.saveMovie(movieResponse);

                ActorsApiResponse actorsResponse = restTemplate
                        .getForEntity("movies/characters?id=" + movieResponse.getMovie().getId(), ActorsApiResponse.class).getBody();

                if (actorsResponse != null) {
                    mediaService.saveActors(actorsResponse, movie);
                }
            }

        } catch (HttpClientErrorException e) {
            throw new ApiClientErrorException(e);
        }
    }

    public void getShowsFromApi() throws ApiClientErrorException {

        RestTemplate restTemplate = betaseriesBuilder.build();

        try {
            ShowApiResponse showsResponse = restTemplate.getForEntity("shows/list?limit=100&order=popularity", ShowApiResponse.class).getBody();

            if (showsResponse != null) {

                List<ShowApiResponse.Show> showsList = showsResponse.getShows();
                log.info("Filling with " + showsList.size() + " shows data...");
                for (ShowApiResponse.Show showResponse : showsList) {
                    Media show = mediaService.saveShow(showResponse);

                    ActorsApiResponse actorsResponse = restTemplate
                            .getForEntity("shows/characters?id=" + showResponse.getId(), ActorsApiResponse.class).getBody();

                    if (actorsResponse != null) {
                        mediaService.saveActors(actorsResponse, show);
                    }
                }
            }

        } catch (HttpClientErrorException e) {
            throw new ApiClientErrorException(e);
        }
    }

    private List<String> getTmdbIdList() {
        RestTemplate restTemplate = tmdbBuilder.build();

        List<String> idList = new ArrayList<>();

        try {
            for (int i = 1; i <= 5; i++) {
                JsonNode entityJson =
                        restTemplate.getForEntity("movie/top_rated?language=fr-FR&page=" + i, JsonNode.class).getBody();

                if (entityJson != null) {
                    entityJson.findPath("results").elements()
                            .forEachRemaining(e -> idList.add(e.findPath("id").asText()));
                }
            }
        } catch (HttpClientErrorException e) {
            log.warn("An error occured while trying to get movies IDs from TMDB");
        }

        return idList;
    }
}