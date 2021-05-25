package com.example.marketRecognizerApp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.marketRecognizerApp.room.RoomConverters

/*
 * This is a modelling class for room and retrofit.
 * Entity is used for creating the table.
 * TypeConverters is used for converting complex structures into primitive types for database operations(Json format , objects and lists ...).
 */
class RoomModels() {
    @Entity(tableName = "LoginTable")
    @TypeConverters(RoomConverters::class)
    data class LoginModel(
        @ColumnInfo(name = "pk") @PrimaryKey(autoGenerate = false) val pk: Int = 0,
        @ColumnInfo(name = "userName") val userName: String? = null,
        @ColumnInfo(name = "rememberMe") val rememberMe: Boolean = false
    )

}

class ApiModels() {
    data class LoginModel(
        val userName: String? = null,
        val password: String? = null,
        val rememberMe: Boolean = false,
        val biometricObject: Any? = null
    )
}

