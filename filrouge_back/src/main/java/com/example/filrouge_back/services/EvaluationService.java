package com.example.filrouge_back.services;

import com.example.filrouge_back.entities.Evaluation;
import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.exceptions.ResourceNotFoundException;
import com.example.filrouge_back.mappers.EvaluationMapper;
import com.example.filrouge_back.models.entitydtos.EvaluationDTO;
import com.example.filrouge_back.repositories.EvaluationRepository;
import com.example.filrouge_back.repositories.MediaRepository;
import com.example.filrouge_back.repositories.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final UserEntityRepository userEntityRepository;
    private final MediaRepository mediaRepository;
    private final EvaluationRepository evaluationRepository;
    private final EvaluationMapper evaluationMapper;

    public EvaluationDTO createEvaluation(EvaluationDTO evaluationDTO) {
        Optional<UserEntity> userEntity = userEntityRepository.findById(evaluationDTO.getUserId());
        Optional<Media> media = mediaRepository.findById(evaluationDTO.getMediaId());

        if (userEntity.isEmpty()) {
            throw new ResourceNotFoundException("User not found at id " + evaluationDTO.getUserId());
        } else if (media.isEmpty()) {
            throw new ResourceNotFoundException("Media not found at id " + evaluationDTO.getMediaId());
        } else {
            Evaluation evaluation = evaluationMapper.evaluationDTOToEvaluation(evaluationDTO);
            evaluation.setUser(userEntity.get());
            evaluation.setMedia(media.get());

            return evaluationMapper.evaluationToEvaluationDTO(evaluationRepository.save(evaluation));
        }
    }

    public EvaluationDTO updateEvaluation(UUID evaluationId, EvaluationDTO updatedEvaluationDTO) {
        Optional<Evaluation> existingEvaluation = evaluationRepository.findById(evaluationId);

        if (existingEvaluation.isPresent()) {
            Evaluation updatedEvaluation = evaluationMapper.evaluationDTOToEvaluation(updatedEvaluationDTO);
            updatedEvaluation.setId(existingEvaluation.get().getId());

            return evaluationMapper.evaluationToEvaluationDTO(evaluationRepository.save(updatedEvaluation));
        } else {
            throw new ResourceNotFoundException("Evaluation not found at id " + evaluationId);
        }
    }

    public void deleteEvaluation(UUID evaluationId) {
        Optional<Evaluation> foundEvaluation = evaluationRepository.findById(evaluationId);

        if (foundEvaluation.isPresent()) {
            evaluationRepository.delete(foundEvaluation.get());
        } else {
            throw new ResourceNotFoundException("Evaluation not found at id " + evaluationId);
        }
    }
}
