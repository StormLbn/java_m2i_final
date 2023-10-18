package com.example.filrouge_back.models.apidtos;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ShowApiResponse {

    private List<Show> shows;

    @Data
    public static class Show {
        private String id;
        private String title;
        private String description;
        private int seasons;
        private int creation;
        private List<PersonApiResponse> showrunners;
        private Map<String, String> genres;
        private int length;
        private String status;
        private ImageObject images;

        @Data
        public static class ImageObject {
            private String poster;
        }
    }
}
