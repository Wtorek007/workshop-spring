package com.pivovarit.movies.domain;

import com.pivovarit.movies.api.MovieDto;
import com.pivovarit.movies.api.MovieTypeDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieFacadeTest {

    @Test
    public void shouldAddMovie() throws Exception {
        MovieFacade movieFacade = MovieFacade.inMemory();

        movieFacade.addMovie(new MovieDto(42L, "spiderman", new MovieTypeDto("NEW"), "details"));
        Set<MovieDto> allMovies = movieFacade.findAllMovies();

        // TODO asercje
    }

    @Test
    public void shouldReturnRentalPrice() throws Exception {

        MovieFacade movieFacade = MovieFacade.instance(new InMemoryMovieRepository(), new RentalPriceCalculator(getPrices()), new InMemoryMovieDetails());

        Optional<Integer> newPrice = movieFacade.getPriceFor("NEW");
        Optional<Integer> oldPrice = movieFacade.getPriceFor("OLD");
        Optional<Integer> regularPrice = movieFacade.getPriceFor("REGULAR");
        Optional<Integer> unknown = movieFacade.getPriceFor("ajshdkjashdakjshdkajs");

        assertThat(newPrice).contains(20);
        assertThat(oldPrice).contains(10);
        assertThat(regularPrice).contains(5);
        assertThat(unknown).isEmpty();
    }

    private static Map<String, Integer> getPrices() {
        Map<String, Integer> map = new HashMap<>();
        map.put("NEW", 20);
        map.put("OLD", 10);
        map.put("REGULAR", 5);
        return map;
    }

}