package com.example.filrouge_back.mappers;


import com.example.filrouge_back.entities.Evaluation;
import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.models.entitydtos.EvaluationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper
public interface EvaluationMapper {

    Evaluation evaluationDTOToEvaluation(EvaluationDTO evaluationDTO);

    @Mapping(source = "media", target = "mediaId", qualifiedByName = "getMediaIdFromEvaluation")
    @Mapping(source = "media", target = "mediaTitle", qualifiedByName = "getMediaTitleFromEvaluation")
    @Mapping(source = "user", target = "userId", qualifiedByName = "getUserIdFromEvaluation")
    @Mapping(source = "user", target = "userName", qualifiedByName = "getUserNameFromUser")
    EvaluationDTO evaluationToEvaluationDTO(Evaluation evaluation);

    List<EvaluationDTO> evaluationListToEvaluationDTOList(List<Evaluation> evaluations);

    @Named("getMediaIdFromEvaluation")
    static UUID getMediaIdFromEvaluation(Media media) {
        return media.getId();
    }

    @Named("getMediaTitleFromEvaluation")
    static String getMediaTitleFromEvaluation(Media media) {
        return media.getTitle();
    }

    @Named("getUserIdFromEvaluation")
    static UUID getUserIdFromEvaluation(UserEntity user) {
        return user.getId();
    }

    @Named("getUserNameFromUser")
    static String getUserNameFromUser(UserEntity user) {
        return user.getPseudo();
    }

}

