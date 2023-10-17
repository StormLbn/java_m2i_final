package com.example.filrouge_back.mappers;


import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.models.MediaDTO;
import org.mapstruct.Mapper;

@Mapper
public interface MediaMapper {
    MediaDTO mediaToMediaDTO(Media media);
}
