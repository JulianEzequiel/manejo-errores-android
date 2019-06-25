package com.bsas.androiddevs.manejoerrores.presenter;

import com.bsas.androiddevs.manejoerrores.manager.MovieManager;
import com.bsas.androiddevs.manejoerrores.ui.view.MainView;

public class MainPresenter extends BasePresenter<MainView> {

    private MovieManager movieManager;

    public MainPresenter() {
        this.movieManager = new MovieManager();
    }

    public void getMovies() {
        //TODO enviar listener
        this.movieManager.getMovies();
    }

}
