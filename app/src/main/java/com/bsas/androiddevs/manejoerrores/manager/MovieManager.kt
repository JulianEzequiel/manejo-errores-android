package com.bsas.androiddevs.manejoerrores.manager

import androidx.lifecycle.LiveData
import com.bsas.androiddevs.manejoerrores.common.Movie
import com.bsas.androiddevs.manejoerrores.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieManager {

    private val movieRepository: MovieRepository = MovieRepository()

    suspend fun getMovies(): List<Movie> {
        return movieRepository.getMovies()
    }

}