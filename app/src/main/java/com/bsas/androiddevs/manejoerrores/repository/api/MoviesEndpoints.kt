package com.bsas.androiddevs.manejoerrores.repository.api

import com.bsas.androiddevs.manejoerrores.repository.api.dto.GetMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesEndpoints {

    @GET("/")
    suspend fun getMovies(@Query("s") query: String,
                          @Query("type") movieType: String = MoviesApiClient.MOVIE_TYPE_QUERY,
                          @Query("apikey") apiKey: String = MoviesApiClient.API_KEY): Response<GetMoviesResponse>


}