package com.example.b2CFinancialApp.di.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.b2CFinancialApp.Constants
import com.example.b2CFinancialApp.di.qualifers.ActivityContext
import com.example.b2CFinancialApp.di.scopes.PerActivity
import com.example.b2CFinancialApp.retrofit.RetrofitClient
import com.example.b2CFinancialApp.retrofit.RetrofitInterface
import com.example.b2CFinancialApp.room.AppDatabase
import dagger.Module
import dagger.Provides

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
