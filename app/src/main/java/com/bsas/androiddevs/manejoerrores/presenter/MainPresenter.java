package com.bsas.androiddevs.manejoerrores.presenter;

import com.bsas.androiddevs.manejoerrores.R;
import com.bsas.androiddevs.manejoerrores.common.Movie;
import com.bsas.androiddevs.manejoerrores.manager.MovieManager;
import com.bsas.androiddevs.manejoerrores.manager.listener.GetMoviesObserver;
import com.bsas.androiddevs.manejoerrores.ui.view.MainView;

import java.util.List;

public class MainPresenter extends BasePresenter<MainView> {

    private MovieManager movieManager;

    public MainPresenter() {
        this.movieManager = new MovieManager();
    }

    public void getMovies() {
        this.movieManager.getMovies(new GetMoviesObserver() {
            @Override
            public void onMoviesObtained(List<Movie> movies) {
                if (MainPresenter.this.view != null) MainPresenter.this.view.displayMovies(movies);
            }

            @Override
            public void onErrorObtainingMovies() {
                if (MainPresenter.this.view != null)
                    MainPresenter.this.view.displayErrorMessage(R.string.error_obtaining_movies);
            }
        });
    }

}
