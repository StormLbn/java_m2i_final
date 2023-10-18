//package com.example.filrouge_back.controllers;
//
//import com.example.filrouge_back.models.EvaluationDTO;
//import com.example.filrouge_back.services.EvaluationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/evaluation")
//public class EvaluationRestController {
//    private final EvaluationService evaluationService;
//
//    @Autowired
//    public EvaluationRestController(EvaluationService evaluationService) {
//        this.evaluationService = evaluationService;
//    }
//
//    @PostMapping("/{userId}/{mediaId}")
//    public ResponseEntity<EvaluationDTO> createEvaluation(@PathVariable UUID userId,
//                                                          @PathVariable UUID mediaId,
//                                                          @RequestBody EvaluationDTO evaluationDTO) {
//        EvaluationDTO createdEvaluation = evaluationService.createEvaluation(userId, mediaId, evaluationDTO);
//        return new ResponseEntity<>(createdEvaluation, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{userId}/{evaluationId}")
//    public ResponseEntity<EvaluationDTO> updateEvaluation(@PathVariable UUID userId,
//                                                          @PathVariable UUID evaluationId,
//                                                          @RequestBody EvaluationDTO updatedEvaluationDTO) {
//        EvaluationDTO updatedEvaluation = evaluationService.updateEvaluation(userId, evaluationId, updatedEvaluationDTO);
//        if (updatedEvaluation != null) {
//            return ResponseEntity.ok(updatedEvaluation);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{userId}/{evaluationId}")
//    public ResponseEntity<Void> deleteEvaluation(@PathVariable UUID userId, @PathVariable UUID evaluationId) {
//        if (evaluationService.deleteEvaluation(userId, evaluationId)) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
