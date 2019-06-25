package com.bsas.androiddevs.manejoerrores.manager.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MoviesApiClient {

    private static final String URL = "http://www.omdbapi.com/";
    private static final String API_KEY = "7a516540";

    private static final int TIMEOUT_IN_SECONDS = 15;
    private static MoviesApiClient _selfInstance;
    private MoviesEndpoints moviesEndpoints;

    private MoviesApiClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS).connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);

        OkHttpClient client = builder.build();
        Retrofit retrofitClient = (new retrofit2.Retrofit.Builder()).baseUrl(URL).client(client).addConverterFactory(JacksonConverterFactory.create()).build();
        this.moviesEndpoints = retrofitClient.create(MoviesEndpoints.class);
    }

    public static MoviesApiClient getInstance() {
        if (_selfInstance == null) {
            _selfInstance = new MoviesApiClient();
        }
        return _selfInstance;
    }


}
