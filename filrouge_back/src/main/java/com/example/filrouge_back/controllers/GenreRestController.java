package com.example.filrouge_back.controllers;

import com.example.filrouge_back.models.entitydtos.GenreDTO;
import com.example.filrouge_back.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
@RequiredArgsConstructor
public class GenreRestController {

    private final GenreService genreService;

    @GetMapping("/all")
    public List<GenreDTO> getAllGenres() {
        return genreService.getAllGenres();
    }
}
