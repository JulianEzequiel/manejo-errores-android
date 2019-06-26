package com.bsas.androiddevs.manejoerrores.manager;

import android.content.Context;

import com.bsas.androiddevs.manejoerrores.common.exception.DbException;
import com.bsas.androiddevs.manejoerrores.manager.listener.GetMoviesObserver;
import com.bsas.androiddevs.manejoerrores.repository.MovieRepository;

public class MovieManager {

    private MovieRepository movieRepository;

    public MovieManager(Context context) {
        this.movieRepository = new MovieRepository(context);
    }

    public void getMovies(GetMoviesObserver observer) throws DbException {
        this.movieRepository.getMovies(observer);
    }


}
