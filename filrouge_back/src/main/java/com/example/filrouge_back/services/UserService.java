package com.example.filrouge_back.services;


import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.mappers.UserMapper;
import com.example.filrouge_back.models.entitydtos.UserDTO;
import com.example.filrouge_back.repositories.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;



    public UserDTO updateUser(UUID userId, UserDTO updatedUserDTO) {
        Optional<UserEntity> optionalUser = userEntityRepository.findById(userId);

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();

            if (updatedUserDTO.getMail() != null) {
                user.setMail(updatedUserDTO.getMail());
            }
            if (updatedUserDTO.getPseudo() != null) {
                user.setPseudo(updatedUserDTO.getPseudo());
            }
            if (updatedUserDTO.getBirthDate() != null) {
                user.setBirthDate(updatedUserDTO.getBirthDate());
            }
            if (updatedUserDTO.getPassword() != null) {
                user.setPassword(updatedUserDTO.getPassword());
            }
            userEntityRepository.save(user);


            UserDTO updatedUserDto = userMapper.userToUserDto(user);

            return updatedUserDto;
        } else {
            return null;
        }
    }



    public UserEntity getUserById(UUID userId) {

        return userEntityRepository.findById(userId).orElse(null);
    }

    public List<Genre> getFavoriteGenresByUserId(UUID userId) {
        Optional<UserEntity> optionalUser = userEntityRepository.findById(userId);

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            return user.getGenres();
        } else {
            return null;
        }
    }

}


