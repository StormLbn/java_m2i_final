package com.example.filrouge_back.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some required data is missing or null")
public class RequiredElementMissingException extends RuntimeException {
    public RequiredElementMissingException(String message) {
        super(message);
    }
}
