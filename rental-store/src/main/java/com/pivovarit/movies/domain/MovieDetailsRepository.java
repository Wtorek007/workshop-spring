package com.pivovarit.movies.domain;

interface MovieDetailsRepository {
    MovieDetails findById(long id);
}
