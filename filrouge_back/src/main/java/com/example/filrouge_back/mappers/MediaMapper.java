package com.example.filrouge_back.mappers;

import com.example.filrouge_back.dto.MediaDTO;
import com.example.filrouge_back.entities.Media;
import org.mapstruct.Mapper;

@Mapper
public interface MediaMapper {
    MediaDTO mediaToMediaDTO(Media media);
}
