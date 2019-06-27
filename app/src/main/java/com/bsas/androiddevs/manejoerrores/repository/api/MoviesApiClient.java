package com.bsas.androiddevs.manejoerrores.repository.api;

import com.bsas.androiddevs.manejoerrores.common.logging.ExceptionLogger;
import com.bsas.androiddevs.manejoerrores.repository.api.callback.EndpointCallback;
import com.bsas.androiddevs.manejoerrores.repository.api.dto.GetMoviesResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MoviesApiClient {

    private static final String URL = "http://www.omdbapi.com/";
    private static final String API_KEY = "7a516540";

    private static final String MOVIE_TYPE_QUERY = "movie";
    private static final int TIMEOUT_IN_SECONDS = 15;

    private static MoviesApiClient selfInstance;

    private MoviesEndpoints moviesEndpoints;

    private MoviesApiClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS).connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);

        OkHttpClient client = builder.build();
        Retrofit retrofitClient = (new retrofit2.Retrofit.Builder()).baseUrl(URL).client(client).addConverterFactory(JacksonConverterFactory.create()).build();
        this.moviesEndpoints = retrofitClient.create(MoviesEndpoints.class);
    }

    public static MoviesApiClient getInstance() {
        if (selfInstance == null) {
            selfInstance = new MoviesApiClient();
        }
        return selfInstance;
    }


    public void getMovies(EndpointCallback<GetMoviesResponse> endpointCallback) {
        Call<GetMoviesResponse> call = this.moviesEndpoints.getMovies(API_KEY, "Avengers", MOVIE_TYPE_QUERY);

        this.enqueEndpointCall(endpointCallback, call);
    }

    @SuppressWarnings("unchecked")
    private <T, Y> void enqueEndpointCall(final EndpointCallback<T> endpointCallback, Call<Y> call) {
        call.enqueue(new Callback<Y>() {
            @Override
            public void onResponse(Call<Y> call, Response<Y> response) {
                if (response.isSuccessful()) {
                    endpointCallback.onSuccess((T) response.body());
                } else {
                    endpointCallback.onFailure(null);
                }
            }

            @Override
            public void onFailure(Call<Y> call, Throwable t) {
                ExceptionLogger.error(t);
                endpointCallback.onFailure(t);
            }
        });
    }
}
