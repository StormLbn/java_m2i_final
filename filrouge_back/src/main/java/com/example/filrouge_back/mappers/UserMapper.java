package com.example.filrouge_back.mappers;


import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.models.entitydtos.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserEntity userDtoToUser(UserDTO userDTO);
    UserDTO userToUserDto(UserEntity user);
}
