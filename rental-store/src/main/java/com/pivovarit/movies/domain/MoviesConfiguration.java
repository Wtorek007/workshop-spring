package com.pivovarit.movies.domain;

import com.pivovarit.movies.api.MovieDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties
class MoviesConfiguration {

    public static void main(String[] args) {

        List<MovieDto> movieDtos = Arrays.asList(new MovieDto(42l, "movies", null, "flasd"));

        List<Movie> collect = movieDtos.stream()
          .map(toMovie())
          .collect(Collectors.toList());
    }

    private static Function<MovieDto, Movie> toMovie() {
        return movieDto -> new Movie(new MovieId(movieDto.getId()), movieDto.getTitle(), MovieType
          .valueOf(movieDto.getType().getMovieType()));
    }

    @Value("${rental.prices.NEW}")
    private String foo;

    @Bean
    @Primary
    MovieFacade inMemoryMovieFacade() {
        return MovieFacade.inMemory();
    }

    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    RentalPriceCalculator rentalPriceCalculator(
      PricesConfiguration pricesConfiguration) {
        return new RentalPriceCalculator(pricesConfiguration.getPrices());
    }

    @Bean
    @Profile("prod")
    MovieDetailsRepository movieDetailsRepository() {
        return new RestTemplateMovieDetails();
    }

    @Bean
    @Profile("dev")
    MovieRepository inMemoryMovieRepository() {
        return new InMemoryMovieRepository();
    }

    @Bean
    @Profile("prod")
    MovieRepository jdbcMovieRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcTemplateMovieRepository(jdbcTemplate);
    }


    @Bean
    @Primary
    @Profile("prod")
    MovieFacade movieFacade(MovieRepository movieRepository, RentalPriceCalculator rentalPriceCalculator, MovieDetailsRepository movieDetailsRepository) {
        return MovieFacade.instance(movieRepository, rentalPriceCalculator, movieDetailsRepository);
    }
}
