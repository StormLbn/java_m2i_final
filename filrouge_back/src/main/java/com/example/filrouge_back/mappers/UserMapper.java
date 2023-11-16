package com.example.filrouge_back.mappers;


import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.models.authdtos.AuthRequest;
import com.example.filrouge_back.models.entitydtos.UserDisplayDTO;
import com.example.filrouge_back.models.entitydtos.UserEditDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public UserEditDTO userToUserEditDto(UserEntity user) {
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

    public UserDisplayDTO userToUserDisplayDto(UserEntity user) {
        if (user == null) {
            return null;
        } else {
            List<String> genres = new ArrayList<>();
            if (user.getGenres() != null) {
                user.getGenres().forEach(genre -> genres.add(genre.getGenreName()));
            }
            return UserDisplayDTO.builder()
                    .id(user.getId())
                    .pseudo(user.getPseudo())
                    .mail(user.getMail())
                    .birthDate(user.getBirthDate())
                    .genres(genres)
                    .build();
        }
    }

    public UserEntity authRequestToNewUserEntity(AuthRequest authRequest) {
        if (authRequest == null) {
            return null;
        } else {
            return UserEntity.builder()
                    .mail(authRequest.getMail())
                    .pseudo(authRequest.getPseudo())
                    .birthDate(authRequest.getBirthDate())
                    .build();
        }
    }
}
