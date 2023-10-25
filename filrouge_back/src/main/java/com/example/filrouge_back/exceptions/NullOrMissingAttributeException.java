package com.example.filrouge_back.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some data is missing or null")
public class NullOrMissingAttributeException extends RuntimeException {
    public NullOrMissingAttributeException(String message) {
        super(message);
    }
}
