package com.example.nananokugarbage.Interface

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.nananokugarbage.Constant
import com.example.nananokugarbage.Model.CityBlock

@Dao
interface ContactsDao {
    @Query("SELECT * FROM ${Constant.CITY_BLOCK_TABLE}")
    fun getAll(): List<CityBlock>

    @Insert
    fun insertAll(vararg cityBlocks: CityBlock)

    @Delete
    fun delete(cityBlocks: CityBlock)
}
