package com.bsas.androiddevs.manejoerrores.ui.activity;

import android.os.Bundle;

import com.bsas.androiddevs.manejoerrores.R;
import com.bsas.androiddevs.manejoerrores.presenter.MainPresenter;
import com.bsas.androiddevs.manejoerrores.presenter.PresenterProvider;
import com.bsas.androiddevs.manejoerrores.ui.view.MainView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.presenter.attachView(this);
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
        return PresenterProvider.get().getPresenter(MainPresenter.class);
    }

}
