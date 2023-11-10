package com.example.filrouge_back.exceptions;

import org.springframework.web.client.HttpClientErrorException;

public class ApiClientErrorException extends Exception {

    public ApiClientErrorException(HttpClientErrorException error) {
        super(error);
    }
}
