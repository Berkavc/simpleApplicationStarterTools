package com.example.b2CFinancialApp.di.modules

import android.app.Application
import android.content.Context
import com.example.b2CFinancialApp.di.qualifers.AppContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val mApp: Application) {

    @Provides
    @Singleton
    internal fun provideApplication() = mApp

    @Provides
    @Singleton
    @AppContext
    internal fun provideAppContext(): Context = mApp

}