package com.example.b2CFinancialApp

import android.app.Application
import com.example.b2CFinancialApp.di.components.AppComponent
import com.example.b2CFinancialApp.di.components.DaggerAppComponent
import com.example.b2CFinancialApp.di.modules.AppModule
import timber.log.Timber

class B2CFinancialApplication : Application() {
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