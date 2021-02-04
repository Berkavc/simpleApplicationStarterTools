package com.example.b2CFinancialApp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.b2CFinancialApp.room.RoomConverters

/*
 * This is a dummy modelling class for room and retrofit.
 * DummyListModel will be a table inside the database with these annotations.
 * Entity is used for creating the table.
 * TypeConverters is used for converting complex structures into primitive types for database operations(Json format , objects and lists ...).
 */
class DummyModels() {
    @Entity(tableName = "DummyTable")
    @TypeConverters(RoomConverters::class)
    data class DummyListModel(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = true) val pk: Int = 0,
        @ColumnInfo(name = "dummy_1") val dummy_1: Int? = null,
        @ColumnInfo(name = "dummy_2") val dummy_2: String? = null,
        @ColumnInfo(name = "dummy_3") val dummy_3: List<DummyListModel2?> = listOf()
    )

    @TypeConverters(RoomConverters::class)
    data class DummyListModel2(
        val id: Int? = null
    )

}