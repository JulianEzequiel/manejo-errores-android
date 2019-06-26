package com.bsas.androiddevs.manejoerrores.ui.application

import android.app.Application
import com.bsas.androiddevs.manejoerrores.repository.db.AppDatabase
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

class MovieAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())
        AppDatabase.configure(this.applicationContext)
    }
}
