package com.pivovarit.movies.api;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public class MovieTypeDto {
    private final String movieType;

    @JsonCreator
    public MovieTypeDto(String movieType) {
        this.movieType = movieType;
    }

    public String getMovieType() {
        return movieType;
    }

    @Override
    public String toString() {
        return "MovieTypeDto{" +
          "movieType='" + movieType + '\'' +
          '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieTypeDto that = (MovieTypeDto) o;
        return Objects.equals(movieType, that.movieType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieType);
    }
}
