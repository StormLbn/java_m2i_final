package com.example.filrouge_back.mappers;


import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.models.entitydtos.MediaDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MediaMapper {
    MediaDTO mediaToMediaDTO(Media media);

    List<MediaDTO> mediaListToMediaDTOList(List<Media> mediaList);
}
