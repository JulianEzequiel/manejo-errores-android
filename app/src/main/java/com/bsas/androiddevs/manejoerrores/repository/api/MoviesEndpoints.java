package com.bsas.androiddevs.manejoerrores.repository.api;

import com.bsas.androiddevs.manejoerrores.repository.api.dto.GetMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesEndpoints {

    @GET
    Call<GetMoviesResponse> getMovies(@Query("apikey") String apiKey,
                                      @Query("s") String query,
                                      @Query("type") String movieType);

}
