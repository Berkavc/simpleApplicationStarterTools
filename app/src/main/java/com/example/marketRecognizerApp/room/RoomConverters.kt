package com.example.marketRecognizerApp.room

import androidx.room.TypeConverter
import com.example.marketRecognizerApp.models.RoomModels
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/*
 * This class is a converter class which is used for converting complex structures into primitive types for database operations(Json format , objects and lists ...).
 */
class RoomConverters {
    private val gson = Gson()

    @TypeConverter
    fun stringToLoginModel(input: String?): RoomModels.LoginModel? {
        return input?.let {
            gson.fromJson(it, RoomModels.LoginModel::class.java)
        }
    }

    @TypeConverter
    fun loginModelToString(input: RoomModels.LoginModel?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }


    @TypeConverter
    fun jsonToString(input: JsonObject?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }

    @TypeConverter
    fun stringToJson(input: String?): JsonObject? {
        return input?.let {
            gson.fromJson(it, JsonObject::class.java)
        }
    }


}
