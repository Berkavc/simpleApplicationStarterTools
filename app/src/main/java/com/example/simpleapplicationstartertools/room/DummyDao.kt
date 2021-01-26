package com.example.simpleapplicationstartertools.room

import androidx.room.*
import com.example.simpleapplicationstartertools.models.DummyModels

/*
 * Base Data access object class write your queries into this class.
 */

@Dao
interface DummyDao {

    //Some standard query examples.

    @Query("SELECT * FROM DummyTable")
    fun getAllDummyItems(): List<DummyModels.DummyListModel?>

    @Query("SELECT * FROM DummyTable WHERE pk = :itemId ")
    fun getDummyItem(itemId: Int?): DummyModels.DummyListModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDummyList(dummyList: DummyModels.DummyListModel?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(gameList: MutableList<DummyModels.DummyListModel?>)

    @Query("DELETE FROM DummyTable WHERE pk = :itemId")
    fun deleteDummyList(itemId: Int?)

}