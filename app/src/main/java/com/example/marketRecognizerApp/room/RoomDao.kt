package com.example.marketRecognizerApp.room

import androidx.room.*
import com.example.marketRecognizerApp.models.RoomModels

/*
 * Base Data access object class write your queries into this class.
 */

@Dao
interface RoomDao {

    @Query("SELECT * FROM LoginTable")
    fun getLoginTable(): RoomModels.LoginModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoginTable(loginModel: RoomModels.LoginModel)

}