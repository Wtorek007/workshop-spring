package com.pivovarit.movies.web;

import com.pivovarit.movies.api.MovieDto;
import com.pivovarit.movies.api.MovieIdDto;
import com.pivovarit.movies.domain.MovieFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/movies")
class RentalStoreController {

    private final MovieFacade movieFacade;

    public RentalStoreController(MovieFacade movieFacade) {
        this.movieFacade = movieFacade;
    }

    @GetMapping("/")
    public Set<MovieDto> getMovies() {
        return movieFacade.findAllMovies();
    }

    @PostMapping()
    public void addMovie(@RequestBody MovieDto movieDto) {
        System.out.println("adding movie: " + movieDto.toString());
        movieFacade.addMovie(movieDto);
    }

    @GetMapping("v1/{id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable("id") Long id, @RequestParam(name = "param", required = false) Optional<String> param) {
        System.out.println("GET /movies/" + id + " called with ?param=" + param.orElse(""));
        return movieFacade.findMovie(new MovieIdDto(id))
          .map(ResponseEntity::ok)
          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("v2/{id}") // z wyjątkami
    public MovieDto getMovieV2(@PathVariable("id") Long id, @RequestParam(name = "param", required = false) Optional<String> param) {
        return movieFacade.findMovie(new MovieIdDto(id))
          .orElseThrow(MovieNotFoundException::new);
    }
}
