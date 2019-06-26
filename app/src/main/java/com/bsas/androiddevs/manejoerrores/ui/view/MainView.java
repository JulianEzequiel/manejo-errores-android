package com.bsas.androiddevs.manejoerrores.ui.view;

import com.bsas.androiddevs.manejoerrores.common.Movie;

import java.util.List;

public interface MainView extends BaseView {
    void displayMovies(List<Movie> movies);
}
