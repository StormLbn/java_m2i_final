package com.example.filrouge_back.services;


import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.exceptions.ResourceNotFoundException;
import com.example.filrouge_back.mappers.UserMapper;
import com.example.filrouge_back.models.entitydtos.UserDisplayDTO;
import com.example.filrouge_back.models.entitydtos.UserEditDTO;
import com.example.filrouge_back.repositories.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    public UserEditDTO updateUser(UUID userId, UserEditDTO updatedUserDTO) {
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


            UserEditDTO updatedUserDto = UserMapper.userToUserEditDto(user);

            return updatedUserDto;
        } else {
            return null;
        }
    }



    public UserDisplayDTO getUserById(UUID userId) {
        Optional<UserEntity> foundUser = userEntityRepository.findById(userId);

        if (foundUser.isPresent()) {
            return UserMapper.userToUserDisplayDto(foundUser.get());
        } else {
            throw new ResourceNotFoundException("User not found at id " + userId);
        }
    }
}


