package com.bsas.androiddevs.manejoerrores.manager;

import com.bsas.androiddevs.manejoerrores.manager.api.MoviesApiClient;

public class MovieManager {

    private MoviesApiClient moviesApiClient;

    public MovieManager() {
        this.moviesApiClient = MoviesApiClient.getInstance();
    }

    public void getMovies() {
        //TODO
    }
}
