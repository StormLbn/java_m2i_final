package com.example.filrouge_back.controllers;

import com.example.filrouge_back.models.entitydtos.GenreDTO;
import com.example.filrouge_back.models.entitydtos.GenreEditDTO;
import com.example.filrouge_back.models.entitydtos.UserDisplayDTO;
import com.example.filrouge_back.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/genre")
@RequiredArgsConstructor
public class GenreRestController {

    private final GenreService genreService;

    @GetMapping("/all")
    public List<GenreDTO> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserDisplayDTO> editUserGenresList(
            @PathVariable UUID userId,
            @RequestBody GenreEditDTO newGenres
            ) {
        return ResponseEntity.ok(genreService.editUserGenresList(userId, newGenres));
    }
}
