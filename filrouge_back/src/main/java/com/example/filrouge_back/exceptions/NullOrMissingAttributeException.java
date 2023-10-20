package com.example.filrouge_back.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NullOrMissingAttributeException extends RuntimeException {
    public NullOrMissingAttributeException(String message) {
        super(message);
    }
}
