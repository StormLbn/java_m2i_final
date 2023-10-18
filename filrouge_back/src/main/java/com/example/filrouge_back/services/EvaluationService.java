//package com.example.filrouge_back.services;
//
//import com.example.filrouge_back.entities.Evaluation;
//import com.example.filrouge_back.entities.Media;
//import com.example.filrouge_back.entities.UserEntity;
//import com.example.filrouge_back.mappers.EvaluationMapper;
//import com.example.filrouge_back.models.EvaluationDTO;
//import com.example.filrouge_back.repositories.EvaluationRepository;
//import com.example.filrouge_back.repositories.MediaRepository;
//import com.example.filrouge_back.repositories.UserEntityRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class EvaluationService {
//    public EvaluationDTO createEvaluation(UUID userId, UUID mediaId, EvaluationDTO evaluationDTO) {
//        Optional<UserEntity> userEntity = UserEntityRepository.findById(userId);
//        Optional<Media> media = MediaRepository.findById(mediaId);
//
//        if (userEntity.isPresent() && media.isPresent()) {
//            Evaluation evaluation = EvaluationMapper.evaluationDTOToEvaluation(evaluationDTO);
//            evaluation.setUser(userEntity.get());
//            evaluation.setMedia(media.get());
//
//            return EvaluationMapper.evaluationToEvaluationDTO(EvaluationMapper.save(evaluation));
//        } else {
//            // Gérer le cas où l'utilisateur ou le média n'a pas été trouvé, par exemple, en lançant une exception.
//            return null;
//        }
//    }
//
//    public EvaluationDTO updateEvaluation(UUID evaluationId, UUID id, EvaluationDTO updatedEvaluationDTO) {
//        Optional<Evaluation> existingEvaluation = EvaluationRepository.findById(evaluationId);
//
//        if (existingEvaluation.isPresent()) {
//            Evaluation updatedEvaluation = Evaluation.evaluationDTOToEvaluation(updatedEvaluationDTO);
//            // Vous n'avez pas besoin de copier manuellement les propriétés ici
//            // Le mapper MapStruct s'en chargera
//            updatedEvaluation.setId(existingEvaluation.get().getId());
//
//            return evaluationMapper.evaluationToEvaluationDTO(EvaluationRepository.save(updatedEvaluation));
//        } else {
//
//            return null;
//        }
//    }
//
//    public boolean deleteEvaluation(UUID userId, UUID evaluationId) {
//    }
//}
