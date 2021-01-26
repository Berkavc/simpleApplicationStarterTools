package com.example.simpleapplicationstartertools.di.components

import android.content.Context
import com.example.simpleapplicationstartertools.di.modules.ActivityModule
import com.example.simpleapplicationstartertools.di.qualifers.ActivityContext
import com.example.simpleapplicationstartertools.di.scopes.PerActivity
import com.example.simpleapplicationstartertools.retrofit.RetrofitInterface
import com.example.simpleapplicationstartertools.room.AppDatabase
import com.example.simpleapplicationstartertools.ui.mainactivity.MainActivity
import com.example.simpleapplicationstartertools.viewmodel.ViewModelModule
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
