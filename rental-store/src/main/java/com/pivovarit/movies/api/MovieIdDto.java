package com.pivovarit.movies.api;

import java.util.Objects;

public class MovieIdDto {
    private final long movieId;

    public MovieIdDto(long movieId) {
        this.movieId = movieId;
    }

    public long getMovieId() {
        return movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieIdDto that = (MovieIdDto) o;
        return movieId == that.movieId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId);
    }
}
