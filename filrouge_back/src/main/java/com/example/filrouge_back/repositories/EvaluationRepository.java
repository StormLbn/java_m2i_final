package com.example.filrouge_back.repositories;

import com.example.filrouge_back.entities.Evaluation;
import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.entities.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, UUID> {

    List<Evaluation> findAllByMedia(Media media, Pageable pageable);
    List<Evaluation> findAllByUser(UserEntity user, Pageable pageable);
}
