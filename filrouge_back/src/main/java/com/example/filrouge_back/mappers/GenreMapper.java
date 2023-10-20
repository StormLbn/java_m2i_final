package com.example.filrouge_back.mappers;


import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.models.entitydtos.GenreDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface GenreMapper {
    Genre genreDTOToGenre(GenreDTO genreDTO);

    GenreDTO genreToGenreDTO(Genre genre);

    List<GenreDTO> genreListToGenreDtoList(List<Genre> genreList);
}

