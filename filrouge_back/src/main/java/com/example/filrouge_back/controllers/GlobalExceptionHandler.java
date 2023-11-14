package com.example.filrouge_back.controllers;

import com.example.filrouge_back.exceptions.DuplicateUserMailException;
import com.example.filrouge_back.exceptions.RequiredElementMissingException;
import com.example.filrouge_back.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(RequiredElementMissingException.class)
    public ResponseEntity<String> handleNullOrMissingAttributeException(RequiredElementMissingException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(DuplicateUserMailException.class)
    public ResponseEntity<String> handleDuplicateUserMailException(DuplicateUserMailException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }
}
