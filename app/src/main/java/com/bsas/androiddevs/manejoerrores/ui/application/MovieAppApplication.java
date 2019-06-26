package com.bsas.androiddevs.manejoerrores.ui.application;

import android.app.Application;

import com.bsas.androiddevs.manejoerrores.repository.db.AppDatabase;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class MovieAppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this, new Crashlytics());
        AppDatabase.configure(this.getApplicationContext());
    }
}
