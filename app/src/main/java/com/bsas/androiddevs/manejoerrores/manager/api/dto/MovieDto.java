package com.bsas.androiddevs.manejoerrores.manager.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDto {

    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("imdbID")
    private String imdbId;

    public String getImdbId() {
        return imdbId;
    }

    public String getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }
}
