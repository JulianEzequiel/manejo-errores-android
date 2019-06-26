package com.bsas.androiddevs.manejoerrores.manager.listener;

import com.bsas.androiddevs.manejoerrores.common.Movie;

import java.util.List;

public interface GetMoviesObserver {

    void onMoviesObtained(List<Movie> movies);

    void onErrorObtainingMovies();

}
