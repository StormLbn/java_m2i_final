package com.example.filrouge_back.services;

import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.mappers.GenreMapper;
import com.example.filrouge_back.models.entitydtos.GenreDTO;
import com.example.filrouge_back.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public List<GenreDTO> getAllGenres() {
        List<Genre> genresList = genreRepository.findAll(Sort.by("genreName"));

        return genreMapper.genreListToGenreDtoList(genresList);
    }
}
