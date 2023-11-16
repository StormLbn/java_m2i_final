package com.example.filrouge_back.services;

import com.example.filrouge_back.entities.Evaluation;
import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.entities.UserEntity;
import com.example.filrouge_back.exceptions.ResourceNotFoundException;
import com.example.filrouge_back.mappers.EvaluationMapper;
import com.example.filrouge_back.mappers.PageMapper;
import com.example.filrouge_back.models.entitydtos.EvaluationDTO;
import com.example.filrouge_back.models.entitydtos.PageDTO;
import com.example.filrouge_back.repositories.EvaluationRepository;
import com.example.filrouge_back.repositories.MediaRepository;
import com.example.filrouge_back.repositories.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final PageMapper pageMapper;

    public PageDTO<EvaluationDTO> getEvaluationsByMedia(UUID mediaId, int page) {
        Optional<Media> foundMedia = mediaRepository.findById(mediaId);

        if (foundMedia.isPresent()) {
            Page<Evaluation> evaluationPage = evaluationRepository.findAllByMedia(
                    foundMedia.get(), PageRequest.of(page, 5));
            return pageMapper.pageToPageDto(
                    evaluationPage,
                    evaluationMapper.evaluationListToEvaluationDTOList(evaluationPage.getContent())
            );
        } else {
            throw new ResourceNotFoundException("Media not found at id " + mediaId);
        }
    }

    public PageDTO<EvaluationDTO> getEvaluationsByUser(UUID userId, int page) {
        Optional<UserEntity> foundUser = userEntityRepository.findById(userId);

        if (foundUser.isPresent()) {
            Page<Evaluation> evaluationPage = evaluationRepository.findAllByUser(
                    foundUser.get(), PageRequest.of(page, 5));
            return pageMapper.pageToPageDto(
                    evaluationPage,
                    evaluationMapper.evaluationListToEvaluationDTOList(evaluationPage.getContent())
            );
        } else {
            throw new ResourceNotFoundException("User not found at id " + userId);
        }
    }

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
        Optional<Evaluation> foundEvaluation = evaluationRepository.findById(evaluationId);

        if (foundEvaluation.isPresent()) {
            Evaluation existingEvaluation = foundEvaluation.get();

            if (updatedEvaluationDTO.getComment() != null) {
                existingEvaluation.setComment(updatedEvaluationDTO.getComment());
            }

            if (updatedEvaluationDTO.getRating() != null) {
                existingEvaluation.setRating(updatedEvaluationDTO.getRating());
            }

            return evaluationMapper.evaluationToEvaluationDTO(evaluationRepository.save(existingEvaluation));
        } else {
            throw new ResourceNotFoundException("Evaluation not found at id " + evaluationId);
        }
    }

    public void deleteEvaluation(UUID evaluationId) {
        if (evaluationRepository.existsById(evaluationId)) {
            evaluationRepository.deleteById(evaluationId);
        } else {
            throw new ResourceNotFoundException("Evaluation not found at id " + evaluationId);
        }
    }
}
