package com.example.nakanokugarbage.Interface

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.nakanokugarbage.Constant
import com.example.nakanokugarbage.Model.CityBlock

@Dao
interface CityBlockDao {
    @Query("SELECT * FROM ${Constant.CITY_BLOCK_TABLE}")
    fun getAll(): List<CityBlock>

    @Insert
    fun insert(vararg cityBlocks: CityBlock)

    @Delete
    fun delete(cityBlocks: CityBlock)

    @Query("DELETE FROM ${Constant.CITY_BLOCK_TABLE}")
    fun deleteAll()
}
