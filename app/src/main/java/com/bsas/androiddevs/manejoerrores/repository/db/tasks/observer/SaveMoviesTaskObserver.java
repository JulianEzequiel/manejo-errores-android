package com.bsas.androiddevs.manejoerrores.repository.db.tasks.observer;

public interface SaveMoviesTaskObserver {
    void onMoviesSaved();

    void onErrorSavingMovies();
}
