package com.example.filrouge_back.models.apidtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MovieApiResponse {

    private Movie movie;

    @Data
    public static class Movie {
        private String id;
        private String tmdb_id;
        private String title;
        private String poster;
        private LocalDate release_date;
        private int length;
        private List<String> genres;
        private String synopsis;
        private Crew crew;
        private OtherTitle other_title;

        @Data
        public static class Crew {

            private List<PersonApiResponse> producers;
            private List<PersonApiResponse> writers;
            private List<PersonApiResponse> directors;
        }

        @Data
        public static class OtherTitle {
            private String title;
        }
    }
}
