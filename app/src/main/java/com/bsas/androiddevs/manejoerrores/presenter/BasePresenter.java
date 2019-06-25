package com.bsas.androiddevs.manejoerrores.presenter;

import com.bsas.androiddevs.manejoerrores.ui.view.BaseView;

public class BasePresenter<T extends BaseView> {

    private T baseView;

    public void attachView(T view) {
        this.baseView = view;
    }

    public void dettachView() {
        this.baseView = null;
    }

}
