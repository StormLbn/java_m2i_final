package com.example.filrouge_back.mappers;


import com.example.filrouge_back.entities.Evaluation;
import com.example.filrouge_back.models.EvaluationDTO;
import org.mapstruct.Mapper;

@Mapper
public interface EvaluationMapper {
    Evaluation evaluationDTOToEvaluation(EvaluationDTO evaluationDTO);

    EvaluationDTO evaluationToEvaluationDTO(Evaluation evaluation);
}

