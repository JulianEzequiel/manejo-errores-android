package com.bsas.androiddevs.manejoerrores.manager;

import com.bsas.androiddevs.manejoerrores.manager.listener.GetMoviesObserver;
import com.bsas.androiddevs.manejoerrores.repository.MovieRepository;

public class MovieManager {

    private MovieRepository movieRepository;

    public MovieManager() {
        this.movieRepository = new MovieRepository();
    }

    public void getMovies(GetMoviesObserver observer) {
        this.movieRepository.getMovies(observer);
    }


}
