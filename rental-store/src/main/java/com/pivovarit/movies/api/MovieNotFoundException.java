package com.pivovarit.movies.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "movie not found")
public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException() {
        super("oopsie");
    }
}
