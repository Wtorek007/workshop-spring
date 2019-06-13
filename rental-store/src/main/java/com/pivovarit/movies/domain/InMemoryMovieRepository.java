package com.pivovarit.movies.domain;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryMovieRepository implements MovieRepository {

    private final Map<MovieId, Movie> db = new ConcurrentHashMap<>();

    @Override
    public MovieId save(Movie movie) {
        db.put(movie.getId(), movie);
        return movie.getId();
    }

    @Override
    public Collection<Movie> findAll() {
        return db.values();
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return db.values().stream()
          .filter(movie -> movie.getTitle().equals(title))
          .findAny();
    }

    @Override
    public Optional<Movie> findById(MovieId id) {
        return Optional.ofNullable(db.get(id));
    }
}
