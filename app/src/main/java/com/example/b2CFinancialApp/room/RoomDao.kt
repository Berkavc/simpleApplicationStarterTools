package com.example.b2CFinancialApp.room

import androidx.room.*
import com.example.b2CFinancialApp.models.room.Models

/*
 * Base Data access object class write your queries into this class.
 */

@Dao
interface RoomDao {

    //Some standard query examples.

    /*  @Query("SELECT * FROM DummyTable")
      fun getAllDummyItems(): List<DummyModels.DummyListModel?>

      @Query("SELECT * FROM DummyTable WHERE pk = :itemId ")
      fun getDummyItem(itemId: Int?): DummyModels.DummyListModel?

      @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun insertDummyList(dummyList: DummyModels.DummyListModel?)

      @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun insertAll(gameList: MutableList<DummyModels.DummyListModel?>)

      @Query("DELETE FROM DummyTable WHERE pk = :itemId")
      fun deleteDummyList(itemId: Int?)*/

    @Query("SELECT * FROM LoginCheckBoxTable")
    fun getCheckBoxState(): Models.LoginCheckBoxModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCheckBoxState(checkBoxModel: Models.LoginCheckBoxModel)

}