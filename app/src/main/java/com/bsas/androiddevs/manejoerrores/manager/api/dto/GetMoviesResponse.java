package com.bsas.androiddevs.manejoerrores.manager.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetMoviesResponse {

    @JsonProperty("Search")
    private List<MovieDto> movies;

    public List<MovieDto> getMovies() {
        return movies;
    }
}
