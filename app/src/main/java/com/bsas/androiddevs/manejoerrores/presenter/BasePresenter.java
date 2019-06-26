package com.bsas.androiddevs.manejoerrores.presenter;

import com.bsas.androiddevs.manejoerrores.ui.view.BaseView;

public class BasePresenter<T extends BaseView> {

    protected T view;

    public void attachView(T view) {
        this.view = view;
    }

    public void dettachView() {
        this.view = null;
    }

}
