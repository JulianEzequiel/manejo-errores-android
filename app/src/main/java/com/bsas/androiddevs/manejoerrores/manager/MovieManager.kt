package com.bsas.androiddevs.manejoerrores.manager

import androidx.lifecycle.LiveData
import com.bsas.androiddevs.manejoerrores.common.Movie
import com.bsas.androiddevs.manejoerrores.common.exception.UIAlertException
import com.bsas.androiddevs.manejoerrores.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.RuntimeException

class MovieManager {

    private val movieRepository: MovieRepository = MovieRepository()

    suspend fun getMovies(): List<Movie> {
        throw UIAlertException("Alerta alertaaa", RuntimeException())
//        return movieRepository.getMovies()
    }

}