package com.example.simpleapplicationstartertools

import android.app.Application
import com.example.simpleapplicationstartertools.di.components.AppComponent
import com.example.simpleapplicationstartertools.di.modules.AppModule
import com.example.simpleapplicationstartertools.di.components.DaggerAppComponent
import timber.log.Timber

class DummyApplication: Application()  {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}