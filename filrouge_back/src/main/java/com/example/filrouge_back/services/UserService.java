package com.example.filrouge_back.services;


import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.mappers.UserMapper;
import com.example.filrouge_back.models.entitydtos.UserDTO;
import com.example.filrouge_back.repositories.UserEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;

    public UserService(UserEntityRepository userEntityRepository, UserMapper userMapper) {
        this.userEntityRepository = userEntityRepository;
        this.userMapper = userMapper;
    }

    public UserEntity updateUser(UUID userId, UserDTO updatedUserDTO) {
        Optional<UserEntity> optionalUser = userEntityRepository.findById(userId);

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
//            UserEntity updatedUser = userMapper.userDtoToUser(updatedUserDTO);

            if (updatedUserDTO.getMail() != null)  {
                user.setMail(updatedUserDTO.getMail());
            }
            if (updatedUserDTO.getPseudo() != null)  {
                user.setPseudo(updatedUserDTO.getPseudo());
            }
            if (updatedUserDTO.getBirthDate() != null)  {
                user.setBirthDate(updatedUserDTO.getBirthDate());
            }
            if (updatedUserDTO.getPassword() != null)  {
                user.setPassword(updatedUserDTO.getPassword());
            }
            userEntityRepository.save(user);

            return user;
        } else {
            return null;
        }
    }
}
