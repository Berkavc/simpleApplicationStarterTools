package com.example.b2CFinancialApp.di.components

import android.content.Context
import com.example.b2CFinancialApp.di.modules.ActivityModule
import com.example.b2CFinancialApp.di.qualifers.ActivityContext
import com.example.b2CFinancialApp.di.scopes.PerActivity
import com.example.b2CFinancialApp.retrofit.RetrofitInterface
import com.example.b2CFinancialApp.room.AppDatabase
import com.example.b2CFinancialApp.ui.mainactivity.MainActivity
import com.example.b2CFinancialApp.viewmodel.ViewModelModule
import dagger.Component

@PerActivity
@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class, ViewModelModule::class]
)
interface ActivityComponent :
        AppComponent {

    @ActivityContext
    fun activityContext(): Context
    fun provideRoomDbBuilder(): AppDatabase
    fun provideRetrofitClientBuilder(): RetrofitInterface


    fun inject(activity: MainActivity)


}
