package com.bsas.androiddevs.manejoerrores.repository.api.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GetMoviesResponse(@JsonProperty("Search") val movies: List<MovieDto>? = null)

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieDto(@JsonProperty("Title") var title: String,
                    @JsonProperty("Year") var year: Int,
                    @JsonProperty("imdbID") var imdbId: String)