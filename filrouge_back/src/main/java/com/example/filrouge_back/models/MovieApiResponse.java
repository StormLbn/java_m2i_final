package com.example.filrouge_back.models;

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
        private String poster;
        private LocalDate release_date;
        private int length;
        private List<String> genres;
        private String synopsis;
        private Crew crew;
        private OtherTitle other_title;

        @Data
        public static class Crew {
            @Data
            private static class Person {
                private int id;
                private String name;
                private String picture;
            }

            private List<Person> producers;
            private List<Person> writers;
            private List<Person> directors;
        }

        @Data
        public static class OtherTitle {
            private String title;
        }
    }
}
