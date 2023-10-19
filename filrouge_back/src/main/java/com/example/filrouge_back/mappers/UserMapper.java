package com.example.filrouge_back.mappers;


import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.models.entitydtos.UserDisplayDTO;
import com.example.filrouge_back.models.entitydtos.UserEditDTO;

import java.util.ArrayList;
import java.util.List;

public abstract class UserMapper {

    public static UserEntity userEditDtoToUser(UserEditDTO userDto) {
        if (userDto == null) {
            return null;
        } else {
            return UserEntity.builder()
                    .pseudo(userDto.getPseudo())
                    .mail(userDto.getMail())
                    .password(userDto.getPassword())
                    .birthDate(userDto.getBirthDate())
                    .build();
        }
    }

    public static UserEditDTO userToUserEditDto(UserEntity user) {
        if (user == null) {
            return null;
        } else {
            return UserEditDTO.builder()
                    .id(user.getId())
                    .pseudo(user.getPseudo())
                    .mail(user.getMail())
                    .birthDate(user.getBirthDate())
                    .build();
        }
    }

    public static UserDisplayDTO userToUserDisplayDto(UserEntity user) {
        if (user == null) {
            return null;
        } else {
            List<String> genres = new ArrayList<>();
            user.getGenres().forEach(genre -> genres.add(genre.getGenreName()));
            return UserDisplayDTO.builder()
                    .id(user.getId())
                    .pseudo(user.getPseudo())
                    .mail(user.getMail())
                    .birthDate(user.getBirthDate())
                    .genres(genres)
                    .build();
        }
    }
}
