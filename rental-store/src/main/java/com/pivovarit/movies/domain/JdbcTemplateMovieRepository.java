package com.pivovarit.movies.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

class JdbcTemplateMovieRepository implements MovieRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public MovieId save(Movie movie) {
        jdbcTemplate.update("INSERT INTO MOVIE VALUES (?, ?, ?)",
          movie.getId().getId(), movie.getTitle(), movie.getType().toString());

        return movie.getId();
    }

    @Override
    public Collection<Movie> findAll() {
        return jdbcTemplate.query("SELECT * FROM MOVIE", toMovie());
    }

    private static RowMapper<Movie> toMovie() {
        return (rs, rowNum) -> new Movie(
          new MovieId(rs.getLong("id")),
          rs.getString("title"),
          MovieType.valueOf(rs.getString("type")));
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public Optional<Movie> findById(MovieId id) {
        return Optional.empty();
    }
}
