package com.pivovarit.movies.api;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

public class MovieDto {

    @NotNull
    @PositiveOrZero
    private final Long id;

    @NotNull
    private final String title;

    @NotNull
    private final MovieTypeDto type;

    private final String details;

    public MovieDto(@NotNull @PositiveOrZero Long id, @NotNull String title, @NotNull MovieTypeDto type, String details) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public MovieTypeDto getType() {
        return type;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "MovieDto{" +
          "id=" + id +
          ", title='" + title + '\'' +
          ", type=" + type +
          ", details='" + details + '\'' +
          '}';
    }
}
