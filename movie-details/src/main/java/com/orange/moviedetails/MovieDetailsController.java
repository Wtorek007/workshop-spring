package com.orange.moviedetails;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MovieDetailsController {


    @GetMapping("details/{id}")
    MovieDetailsResponse details(@PathVariable long id) {
        // TODO
        return new MovieDetailsResponse(42, "lorem ipsum");
    }

    public static class MovieDetailsResponse {

        private final long id;
        private final String details;

        public MovieDetailsResponse(long id, String details) {
            this.id = id;
            this.details = details;
        }

        public long getId() {
            return id;
        }

        public String getDetails() {
            return details;
        }
    }
    // GET /details/{id}
}
