package com.bsas.androiddevs.manejoerrores.common.util

import com.bsas.androiddevs.manejoerrores.common.Movie
import com.bsas.androiddevs.manejoerrores.repository.api.dto.MovieDto
import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb

fun List<MovieDb>.movieDbsToMovies() : List<Movie> = this.map { Movie(it) }

fun List<MovieDto>.movieDtosToMovieDbs() : List<MovieDb> = this.map { MovieDb(it) }