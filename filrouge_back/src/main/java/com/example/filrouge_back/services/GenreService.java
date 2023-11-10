package com.example.filrouge_back.services;

import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.exceptions.NullOrMissingAttributeException;
import com.example.filrouge_back.exceptions.ResourceNotFoundException;
import com.example.filrouge_back.mappers.GenreMapper;
import com.example.filrouge_back.mappers.UserMapper;
import com.example.filrouge_back.models.entitydtos.GenreDTO;
import com.example.filrouge_back.models.entitydtos.GenreEditDTO;
import com.example.filrouge_back.models.entitydtos.UserDisplayDTO;
import com.example.filrouge_back.repositories.GenreRepository;
import com.example.filrouge_back.repositories.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public List<Genre> getGenresById(List<Integer> idList) {
        List<Genre> genres = new ArrayList<>();
        for (Integer id : idList) {
            genres.add(findGenreById(id));
        }
        return genres;
    }

    public List<GenreDTO> getAllGenres() {
        List<Genre> genresList = genreRepository.findAll(Sort.by("genreName"));

        return genreMapper.genreListToGenreDtoList(genresList);
    }

    public Genre findGenreById(Integer genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found at id " + genreId));
    }

    public List<Genre> saveMediaGenresFromGenreNames(List<String> genreNames) {
        List<Genre> mediaGenresList = new ArrayList<>();
        for (String genreName : genreNames) {
            Genre genre;
            if (genreRepository.existsByGenreName(genreName)) {
                genre = genreRepository.findByGenreName(genreName);
            } else {
                genre = genreRepository.save(
                        Genre.builder().genreName(genreName).build()
                );
            }
            mediaGenresList.add(genre);
        }
        return mediaGenresList;
    }
}
