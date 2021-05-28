package com.example.marketRecognizerApp.di.modules

import android.content.Context
import androidx.room.Room
import com.example.marketRecognizerApp.di.qualifiers.RoomDbBuilder
import com.example.marketRecognizerApp.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalDbModule {
    @RoomDbBuilder
    @Provides
    internal fun provideRoomDbBuilder(
       @ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "DummyApplication.db"
        ).fallbackToDestructiveMigration().build()
    }

}
