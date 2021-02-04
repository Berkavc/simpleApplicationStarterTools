package com.example.b2CFinancialApp.room

import androidx.room.TypeConverter
import com.example.b2CFinancialApp.models.DummyModels
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
    fun stringToDummyListModel(input: String?): DummyModels.DummyListModel? {
        return input?.let {
            gson.fromJson(it, DummyModels.DummyListModel::class.java)
        }
    }

    @TypeConverter
    fun dummyListModelToString(input: DummyModels.DummyListModel?): String? {
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

    @TypeConverter
    fun listDummyListModelToString(input: List<DummyModels.DummyListModel2>?): String? {
        return input?.let {
            gson.toJson(input)
        }
    }


    @TypeConverter
    fun stringToListstringToListDummyListModel2(input: String?): List<DummyModels.DummyListModel2>? {
        val type: Type = object : TypeToken<List<DummyModels.DummyListModel2?>>() {}.type
        return input?.let {
            gson.fromJson<ArrayList<DummyModels.DummyListModel2>>(it, type).toList()
        }
    }

    /*
    @TypeConverter
    fun stringToListDummyListModel(input: DummyModels.DummyListModel2?): List<String>? {
        val type: Type = object : TypeToken<List<String>>() {}.type
        return input?.let {
            gson.fromJson<ArrayList<String>>(it,type).toList()
        }
    }
   */

}
