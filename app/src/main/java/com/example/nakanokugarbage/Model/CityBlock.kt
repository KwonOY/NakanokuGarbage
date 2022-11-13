package com.example.nakanokugarbage.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nakanokugarbage.Constant

@Entity(tableName = Constant.CITY_BLOCK_TABLE)
data class CityBlock(
    @PrimaryKey(autoGenerate = true) val id:Int,
    var english_city_name: String,
    var japanese_city_name: String,
    var korean_city_name: String,
    var english_block_name: String,
    var japanese_block_name: String,
    var korean_block_name: String)
