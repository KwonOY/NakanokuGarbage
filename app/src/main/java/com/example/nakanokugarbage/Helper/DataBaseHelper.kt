package com.example.nakanokugarbage.Helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nakanokugarbage.Interface.CityBlockDao
import com.example.nakanokugarbage.Model.CityBlock
import com.example.nakanokugarbage.R

@Database(entities = [CityBlock::class], version = 1, exportSchema = false)
abstract class DataBaseHelper : RoomDatabase() {
    abstract fun cityBlockDaoDao(): CityBlockDao

    companion object {
        private var instance: DataBaseHelper? = null

        @Synchronized
        fun getInstance(context: Context): DataBaseHelper? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseHelper::class.java,
                    context.resources.getString(R.string.database_name)
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}
