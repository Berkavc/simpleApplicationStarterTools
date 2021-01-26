package com.example.simpleapplicationstartertools.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.simpleapplicationstartertools.models.DummyModels

 /*
 * Base room class write your tables inside the entities parameter .
 */
@Database(
    entities = [DummyModels.DummyListModel::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dummyDao(): DummyDao
}
