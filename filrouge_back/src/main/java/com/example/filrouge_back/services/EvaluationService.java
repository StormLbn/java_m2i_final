package com.example.filrouge_back.services;

import com.example.filrouge_back.entities.Evaluation;
import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.entities.UserEntity;
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

        if (userEntity.isPresent() && media.isPresent()) {
            Evaluation evaluation = evaluationMapper.evaluationDTOToEvaluation(evaluationDTO);
            evaluation.setUser(userEntity.get());
            evaluation.setMedia(media.get());

            return evaluationMapper.evaluationToEvaluationDTO(evaluationRepository.save(evaluation));
        } else {
            // TODO Gérer le cas où l'utilisateur ou le média n'a pas été trouvé.
            return null;
        }
    }

    public EvaluationDTO updateEvaluation(UUID evaluationId, EvaluationDTO updatedEvaluationDTO) {
        Optional<Evaluation> existingEvaluation = evaluationRepository.findById(evaluationId);

        if (existingEvaluation.isPresent()) {
            Evaluation updatedEvaluation = evaluationMapper.evaluationDTOToEvaluation(updatedEvaluationDTO);
            updatedEvaluation.setId(existingEvaluation.get().getId());

            return evaluationMapper.evaluationToEvaluationDTO(evaluationRepository.save(updatedEvaluation));
        } else {

            return null;
        }
    }

    public boolean deleteEvaluation(UUID evaluationId) {
        Optional<Evaluation> foundEvaluation = evaluationRepository.findById(evaluationId);

        if (foundEvaluation.isPresent()) {
            evaluationRepository.delete(foundEvaluation.get());

            return true;
        }

        return false;
    }
}
