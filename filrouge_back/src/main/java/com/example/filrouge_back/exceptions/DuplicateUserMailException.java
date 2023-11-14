package com.example.filrouge_back.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "This email address is already in use")
public class DuplicateUserMailException extends RuntimeException {
    public DuplicateUserMailException(String message) {
        super(message);
    }
}
