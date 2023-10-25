package com.example.filrouge_back.controllers;

import com.example.filrouge_back.models.entitydtos.EvaluationDTO;
import com.example.filrouge_back.services.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/evaluation")
@RequiredArgsConstructor
public class EvaluationRestController {

    private final EvaluationService evaluationService;

    @GetMapping("media/{mediaId}/{page}")
    public List<EvaluationDTO> getEvaluationsByMedia(
            @PathVariable("mediaId") UUID mediaId,
            @PathVariable("page") int page
    ) {
        return evaluationService.getEvaluationsByMedia(mediaId, page);
    }

    @GetMapping("user/{userId}/{page}")
    public List<EvaluationDTO> getEvaluationsByUser(
            @PathVariable("userId") UUID userId,
            @PathVariable("page") int page
    ) {
        return evaluationService.getEvaluationsByUser(userId, page);
    }

    @PostMapping("/add")
    public ResponseEntity<EvaluationDTO> createEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        EvaluationDTO createdEvaluation = evaluationService.createEvaluation(evaluationDTO);
        return new ResponseEntity<>(createdEvaluation, HttpStatus.CREATED);
    }

    @PatchMapping("/{evaluationId}")
    public ResponseEntity<EvaluationDTO> updateEvaluation(
            @PathVariable UUID evaluationId,
            @RequestBody EvaluationDTO updatedEvaluationDTO
    ) {
        EvaluationDTO updatedEvaluation = evaluationService.updateEvaluation(
                evaluationId, updatedEvaluationDTO);
        return ResponseEntity.ok(updatedEvaluation);
    }

    @DeleteMapping("/{evaluationId}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable UUID evaluationId) {
        evaluationService.deleteEvaluation(evaluationId);
        return ResponseEntity.ok().build();
    }
}
