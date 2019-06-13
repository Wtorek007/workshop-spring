package com.pivovarit.movies.domain;

import org.springframework.web.client.RestTemplate;

class RestTemplateMovieDetails implements MovieDetailsRepository {

    private final RestTemplate restTemplate = new RestTemplate();

    // TODO wyciągnąć URL do configa
    @Override
    public MovieDetails findById(long id) {
        return restTemplate.getForObject("http://localhost:8090/details/" + id, MovieDetails.class);
    }
}
