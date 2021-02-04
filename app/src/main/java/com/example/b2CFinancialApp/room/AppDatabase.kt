package com.example.b2CFinancialApp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.b2CFinancialApp.models.DummyModels

/*
* Base room class write your tables inside the entities parameter .
*/
@Database(entities = [DummyModels.DummyListModel::class], version = 1, exportSchema = true)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dummyDao(): DummyDao

    companion object {
        //immediately available for all threads
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
                ?: synchronized(LOCK) {
                    instance
                            ?: buildDatabase(context).also {
                                instance = it
                            }
                }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "appdatabase"
        ).build()

    }
}
