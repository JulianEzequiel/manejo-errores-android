package com.bsas.androiddevs.manejoerrores.repository.api

import com.bsas.androiddevs.manejoerrores.common.logging.ExceptionLogger
import com.bsas.androiddevs.manejoerrores.repository.api.dto.GetMoviesResponse
import okhttp3.OkHttpClient
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

class MoviesApiClient private constructor(){

    private val URL = "http://www.omdbapi.com/"
    private val TIMEOUT_IN_SECONDS = 15

    private val moviesEndpoints: MoviesEndpoints

    init {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS).connectTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)

        val client = builder.build()
        val retrofitClient = retrofit2.Retrofit.Builder().baseUrl(URL).client(client).addConverterFactory(JacksonConverterFactory.create()).build()
        this.moviesEndpoints = retrofitClient.create(MoviesEndpoints::class.java)
    }

    companion object {
        val API_KEY = "7a516540"
        val MOVIE_TYPE_QUERY = "movie"

        var moviesApiClient = MoviesApiClient()
        fun instance() : MoviesApiClient = moviesApiClient
    }

    suspend fun getMovies() : GetMoviesResponse? {
        try {
            val response = this.moviesEndpoints.getMovies("Avengers")
            if (response.isSuccessful) {
                return response.body()
            } else {
                throw RuntimeException("Error obtaiing movies")
            }
        } catch (e : Exception) {
            ExceptionLogger.error(e)
            throw RuntimeException("Error obtaiing movies", e)
        }
    }

}