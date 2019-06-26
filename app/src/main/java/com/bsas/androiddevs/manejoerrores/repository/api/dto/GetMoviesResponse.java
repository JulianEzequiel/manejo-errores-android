package com.bsas.androiddevs.manejoerrores.repository.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetMoviesResponse {

    @JsonProperty("Search")
    private List<MovieDto> movies;

    public List<MovieDto> getMovies() {
        return movies;
    }
}
