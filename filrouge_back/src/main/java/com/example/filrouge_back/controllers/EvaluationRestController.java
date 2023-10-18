package com.example.filrouge_back.controllers;

import com.example.filrouge_back.models.entitydtos.EvaluationDTO;
import com.example.filrouge_back.services.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/evaluation")
@RequiredArgsConstructor
public class EvaluationRestController {

    private final EvaluationService evaluationService;

    // TODO GET commentaires d'un média
    // TODO GET commentaires d'un utilisateur

    @PostMapping("/add")
    public ResponseEntity<EvaluationDTO> createEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        EvaluationDTO createdEvaluation = evaluationService.createEvaluation(evaluationDTO);
        return new ResponseEntity<>(createdEvaluation, HttpStatus.CREATED);
    }

    // TODO passer en PATCH
    @PutMapping("/{evaluationId}")
    public ResponseEntity<EvaluationDTO> updateEvaluation(
            @PathVariable UUID evaluationId,
            @RequestBody EvaluationDTO updatedEvaluationDTO
    ) {
        EvaluationDTO updatedEvaluation = evaluationService.updateEvaluation(evaluationId, updatedEvaluationDTO);
        if (updatedEvaluation != null) {
            return ResponseEntity.ok(updatedEvaluation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{evaluationId}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable UUID evaluationId) {
        if (evaluationService.deleteEvaluation(evaluationId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
