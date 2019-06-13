package com.pivovarit.movies.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class MovieDetails {
    private final long id;
    private final String details;

    @JsonCreator
    public MovieDetails(@JsonProperty("id") long id, @JsonProperty("details") String details) {
        this.id = id;
        this.details = details;
    }

    public long getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }
}
