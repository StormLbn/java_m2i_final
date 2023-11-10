package com.example.filrouge_back.services;


import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.exceptions.NullOrMissingAttributeException;
import com.example.filrouge_back.exceptions.ResourceNotFoundException;
import com.example.filrouge_back.mappers.UserMapper;
import com.example.filrouge_back.models.entitydtos.GenreEditDTO;
import com.example.filrouge_back.models.entitydtos.UserDisplayDTO;
import com.example.filrouge_back.models.entitydtos.UserEditDTO;
import com.example.filrouge_back.repositories.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;
    private final GenreService genreService;

    public UserDisplayDTO getUserById(UUID userId) {
        return userMapper.userToUserDisplayDto(findUserById(userId));
    }

    public UserEditDTO updateUser(UUID userId, UserEditDTO updatedUserDTO) {
        UserEntity user = findUserById(userId);

        if (updatedUserDTO.getMail() != null) {
            user.setMail(updatedUserDTO.getMail());
        }
        if (updatedUserDTO.getPseudo() != null) {
            user.setPseudo(updatedUserDTO.getPseudo());
        }
        if (updatedUserDTO.getBirthDate() != null) {
            user.setBirthDate(updatedUserDTO.getBirthDate());
        }
        userEntityRepository.save(user);

        return userMapper.userToUserEditDto(user);
    }

    public UserDisplayDTO editUserGenresList(UUID userId, GenreEditDTO newGenres) {
        UserEntity user = findUserById(userId);

        if (newGenres.getGenreIdList() != null) {
            user.setGenres(genreService.getGenresById(newGenres.getGenreIdList()));
            return userMapper.userToUserDisplayDto(userEntityRepository.save(user));
        } else {
            throw new NullOrMissingAttributeException("New genres Id list is null");
        }
    }

    public UserEntity findUserById(UUID userId) {
        return userEntityRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found at id " + userId));
    }
}


