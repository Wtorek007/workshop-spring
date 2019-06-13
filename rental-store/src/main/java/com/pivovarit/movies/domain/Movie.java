package com.pivovarit.movies.domain;

class Movie {

    private final MovieId id;
    private final String title;
    private final MovieType type;

    Movie(MovieId id, String title, MovieType type) {
        this.id = id;
        this.title = title;
        this.type = type;
    }

    String getTitle() {
        return title;
    }

    MovieType getType() {
        return type;
    }

    MovieId getId() {
        return id;
    }
}
