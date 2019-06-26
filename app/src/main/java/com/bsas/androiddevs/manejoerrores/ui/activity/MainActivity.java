package com.bsas.androiddevs.manejoerrores.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bsas.androiddevs.manejoerrores.R;
import com.bsas.androiddevs.manejoerrores.common.Movie;
import com.bsas.androiddevs.manejoerrores.presenter.MainPresenter;
import com.bsas.androiddevs.manejoerrores.presenter.PresenterProvider;
import com.bsas.androiddevs.manejoerrores.ui.activity.adapter.MoviesRecyclerViewAdapter;
import com.bsas.androiddevs.manejoerrores.ui.view.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    private MoviesRecyclerViewAdapter moviesRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.presenter.attachView(this);

        this.bindViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.presenter.getMovies();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.presenter.dettachView();
    }

    @Override
    protected MainPresenter createPresenter() {
        return PresenterProvider.get().getPresenter(this, MainPresenter.class);
    }

    @Override
    public void displayMovies(List<Movie> movies) {
        this.moviesRecyclerViewAdapter.refreshMovies(movies);
    }

    private void bindViews() {
        RecyclerView moviesRecyclerView = this.findViewById(R.id.movies_recycler_view);
        moviesRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        moviesRecyclerView.setLayoutManager(linearLayoutManager);

        this.moviesRecyclerViewAdapter = new MoviesRecyclerViewAdapter(new ArrayList<Movie>());
        moviesRecyclerView.setAdapter(this.moviesRecyclerViewAdapter);
    }
}
