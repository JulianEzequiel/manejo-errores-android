package com.bsas.androiddevs.manejoerrores.repository.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDto {

    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private int year;
    @JsonProperty("imdbID")
    private String imdbId;

    public String getImdbId() {
        return imdbId;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }
}
