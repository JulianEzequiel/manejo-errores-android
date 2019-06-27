package com.bsas.androiddevs.manejoerrores.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bsas.androiddevs.manejoerrores.presenter.BasePresenter;
import com.bsas.androiddevs.manejoerrores.ui.dialog.GenericErrorDialog;
import com.bsas.androiddevs.manejoerrores.ui.dialog.GenericWarningDialog;
import com.bsas.androiddevs.manejoerrores.ui.view.BaseView;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = this.createPresenter();
    }

    @Override
    public void displayErrorMessage(int messageResource) {
        GenericErrorDialog genericErrorDialog = new GenericErrorDialog();
        genericErrorDialog.setMessageResource(messageResource);
        genericErrorDialog.show(this.getSupportFragmentManager(), null);
    }

    @Override
    public void displayWarningMessage(int messageResource) {
        GenericWarningDialog genericWarningDialog = new GenericWarningDialog();
        genericWarningDialog.setMessageResource(messageResource);
        genericWarningDialog.show(this.getSupportFragmentManager(), null);
    }

    protected abstract T createPresenter();

}
