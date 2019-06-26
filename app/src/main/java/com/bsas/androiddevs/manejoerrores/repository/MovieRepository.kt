package com.bsas.androiddevs.manejoerrores.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.bsas.androiddevs.manejoerrores.common.Movie
import com.bsas.androiddevs.manejoerrores.common.util.movieDbsToMovies
import com.bsas.androiddevs.manejoerrores.common.util.movieDtosToMovieDbs
import com.bsas.androiddevs.manejoerrores.repository.api.MoviesApiClient
import com.bsas.androiddevs.manejoerrores.repository.db.AppDatabase
import com.bsas.androiddevs.manejoerrores.repository.db.dao.MovieDao
import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb

class MovieRepository {

    private val moviesApiClient: MoviesApiClient = MoviesApiClient.instance()
    private val movieDao: MovieDao = AppDatabase.instance().movieDao

    suspend fun getMovies(): List<Movie> {
        if (this.movieDao.countAll() == 0) {
            this.getMoviesFromApiAndSave()
        }
        return this.getAllMoviesFromDb()
    }

    private suspend fun getMoviesFromApiAndSave() {
        val movies = this.moviesApiClient.getMovies()?.movies
        movies?.movieDtosToMovieDbs().apply {
            movieDao.upsertAll(this!!)
        }
    }

    private suspend fun getAllMoviesFromDb(): List<Movie> {
        val movies: List<MovieDb> = this.movieDao.findAll()
        return movies.movieDbsToMovies()
    }

}