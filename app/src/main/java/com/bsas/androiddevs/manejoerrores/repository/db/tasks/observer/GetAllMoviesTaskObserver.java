package com.bsas.androiddevs.manejoerrores.repository.db.tasks.observer;

import com.bsas.androiddevs.manejoerrores.repository.db.entity.MovieDb;

import java.util.List;

public interface GetAllMoviesTaskObserver {
    void onMoviesObtained(List<MovieDb> movieDbs);

    void errorObtainingMovies();
}
