package com.example.simpleapplicationstartertools.di.components

import android.content.Context
import com.example.simpleapplicationstartertools.di.modules.AppModule
import com.example.simpleapplicationstartertools.di.qualifers.AppContext
import com.example.simpleapplicationstartertools.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    @AppContext
    fun appContext(): Context

}
