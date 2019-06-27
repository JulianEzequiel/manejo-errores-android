package com.bsas.androiddevs.manejoerrores.manager

import com.bsas.androiddevs.manejoerrores.common.Movie
import com.bsas.androiddevs.manejoerrores.repository.MovieRepository

class MovieManager {

    private val movieRepository: MovieRepository = MovieRepository()

    suspend fun getMovies(): List<Movie> {
        return movieRepository.getMovies()
//        throw UIErrorException(R.string.error, null)
//        throw UIAlertException(R.string.alert, null)
    }

}