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
    private final UserEntityRepository userEntityRepository;
    private final GenreMapper genreMapper;
    private final UserMapper userMapper;

    public List<GenreDTO> getAllGenres() {
        List<Genre> genresList = genreRepository.findAll(Sort.by("genreName"));

        return genreMapper.genreListToGenreDtoList(genresList);
    }

    public UserDisplayDTO editUserGenresList(UUID userId, GenreEditDTO newGenres) {
        Optional<UserEntity> optionalUser = userEntityRepository.findById(userId);

        UserEntity user = optionalUser.orElseThrow(()
                -> new ResourceNotFoundException("User not found at id " + userId));

        List<Genre> newGenreList = new ArrayList<>();

        if (newGenres.getGenreIdList() != null) {
            for (Integer genreId : newGenres.getGenreIdList()) {
                Optional<Genre> genre = genreRepository.findById(genreId);
                newGenreList.add(genre.orElseThrow(()
                        -> new ResourceNotFoundException("Genre not found at id " + genreId)));
            }

            user.setGenres(newGenreList);
            return userMapper.userToUserDisplayDto(userEntityRepository.save(user));
        } else {
            throw new NullOrMissingAttributeException("New genres Id list is null");
        }
    }
}
