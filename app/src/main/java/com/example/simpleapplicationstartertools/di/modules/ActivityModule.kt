package com.example.simpleapplicationstartertools.di.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.simpleapplicationstartertools.Constants
import com.example.simpleapplicationstartertools.di.qualifers.ActivityContext
import com.example.simpleapplicationstartertools.di.scopes.PerActivity
import com.example.simpleapplicationstartertools.retrofit.RetrofitClient
import com.example.simpleapplicationstartertools.retrofit.RetrofitInterface
import com.example.simpleapplicationstartertools.room.AppDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ActivityModule(private val mActivity: AppCompatActivity) {

    @Provides
    @PerActivity
    @ActivityContext
    internal fun provideActivityContext(): Context {
        return mActivity
    }

    @Provides
    @PerActivity
    internal fun provideRoomDbBuilder(): AppDatabase {
        val db: AppDatabase = Room.databaseBuilder(
            provideActivityContext(),
            AppDatabase::class.java, "GameList.db"
        ).fallbackToDestructiveMigration().build()
        return db
    }

    @Provides
    @PerActivity
    internal fun provideRetrofitClientBuilder(): RetrofitInterface {
        return RetrofitClient.getRetrofitClient(Constants.DUMMY_BASE_URL).create(RetrofitInterface::class.java)
    }

}
