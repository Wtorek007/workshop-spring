package com.pivovarit.movies.domain;

import com.pivovarit.movies.api.MovieDto;
import com.pivovarit.movies.api.MovieIdDto;
import com.pivovarit.movies.api.MovieTypeDto;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieFacade {

    private final RentalPriceCalculator rentalPriceCalculator;
    private final MovieRepository movieRepository;
    private final MovieDetailsRepository movieDetailsRepository;
    private final MovieCreator movieCreator;

    public static MovieFacade inMemory() {
        return new MovieFacade(new RentalPriceCalculator(getPrices()), new InMemoryMovieRepository(), new InMemoryMovieDetails(), new MovieCreator());
    }

    private static Map<String, Integer> getPrices() {
        Map<String, Integer> map = new HashMap<>();
        map.put("NEW", 20);
        map.put("OLD", 10);
        map.put("REGULAR", 5);
        return map;
    }

    static MovieFacade instance(MovieRepository movieRepository, RentalPriceCalculator rentalPriceCalculator, MovieDetailsRepository movieDetailsRepository ) {
        return new MovieFacade(rentalPriceCalculator, movieRepository, movieDetailsRepository, new MovieCreator());
    }

    public MovieFacade(RentalPriceCalculator rentalPriceCalculator, MovieRepository movieRepository, MovieDetailsRepository movieDetailsRepository, MovieCreator movieCreator) {
        this.rentalPriceCalculator = rentalPriceCalculator;
        this.movieRepository = movieRepository;
        this.movieDetailsRepository = movieDetailsRepository;
        this.movieCreator = movieCreator;
    }

    public Optional<MovieDto> findMovie(MovieIdDto id) {
        return movieRepository.findById(new MovieId(id.getMovieId()))
          .map(movieCreator::from);
    }

    public void addMovie(MovieDto movieDto) {
        movieRepository.save(movieCreator.from(movieDto));
    }

    public Optional<Integer> getPriceFor(String type) {
        return rentalPriceCalculator.priceFor(type);
    }

    public Set<MovieDto> findAllMovies() {
        return movieRepository.findAll().stream()
          .map(m -> movieCreator.from(m, movieDetailsRepository.findById(m.getId().getId()).getDetails()))
          .collect(Collectors.toSet());
    }
}
