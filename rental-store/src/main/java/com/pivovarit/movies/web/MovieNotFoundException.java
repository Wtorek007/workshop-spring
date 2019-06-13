package com.pivovarit.movies.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "ej, nie ma filmu")
public class MovieNotFoundException extends RuntimeException {
}
