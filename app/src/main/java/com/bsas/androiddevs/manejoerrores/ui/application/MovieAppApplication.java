package com.bsas.androiddevs.manejoerrores.ui.application;

import android.app.Application;

import com.bsas.androiddevs.manejoerrores.repository.db.AppDatabase;

public class MovieAppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppDatabase.configure(this.getApplicationContext());
    }
}
