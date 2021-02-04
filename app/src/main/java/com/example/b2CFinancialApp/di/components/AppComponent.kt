package com.example.b2CFinancialApp.di.components

import android.content.Context
import com.example.b2CFinancialApp.di.modules.AppModule
import com.example.b2CFinancialApp.di.qualifers.AppContext
import com.example.b2CFinancialApp.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    @AppContext
    fun appContext(): Context

}
