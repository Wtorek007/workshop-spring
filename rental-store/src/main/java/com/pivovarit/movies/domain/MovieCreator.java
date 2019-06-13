package com.pivovarit.movies.domain;

import com.pivovarit.movies.api.MovieDto;
import com.pivovarit.movies.api.MovieTypeDto;
import org.springframework.stereotype.Component;

class MovieCreator {

    public MovieCreator() {
        System.out.println("Tworzymy MovieCreator...");
    }

    public void foo() {
        System.out.println("Hello World");
    }

    Movie from(MovieDto filmDto) {
        return new Movie(new MovieId(filmDto.getId()), filmDto.getTitle(), MovieType.valueOf(filmDto.getType().getMovieType()));
    }

    MovieDto from(Movie movie, String details) {
        return new MovieDto(movie.getId().getId(), movie.getTitle(), new MovieTypeDto(movie.getType().toString()), details);
    }

    MovieDto from(Movie movie) {
        return new MovieDto(movie.getId().getId(), movie.getTitle(), new MovieTypeDto(movie.getType().toString()), null);
    }
}
