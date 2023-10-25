package com.example.filrouge_back.mappers;


import com.example.filrouge_back.entities.Evaluation;
import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.models.entitydtos.EvaluationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper
public interface EvaluationMapper {

    Evaluation evaluationDTOToEvaluation(EvaluationDTO evaluationDTO);

    @Mapping(source = "media", target = "mediaId", qualifiedByName = "getMediaIdFromEvaluation")
    @Mapping(source = "user", target = "userId", qualifiedByName = "getUserIdFromEvaluation")
    EvaluationDTO evaluationToEvaluationDTO(Evaluation evaluation);

    @Named("getMediaIdFromEvaluation")
    public static UUID getMediaIdFromEvaluation(Media media) {
        return media.getId();
    }

    @Named("getUserIdFromEvaluation")
    public static UUID getUserIdFromEvaluation(UserEntity user) {
        return user.getId();
    }
}

