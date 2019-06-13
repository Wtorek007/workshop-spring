package com.pivovarit.movies.domain;

class InMemoryMovieDetails implements MovieDetailsRepository {
    @Override
    public MovieDetails findById(long id) {
        return new MovieDetails(42, id + "-details");
    }
}
