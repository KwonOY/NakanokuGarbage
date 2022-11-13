package com.example.nananokugarbage.Helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nananokugarbage.Interface.ContactsDao
import com.example.nananokugarbage.Model.CityBlock

@Database(entities = [CityBlock::class], version = 1, exportSchema = false)
abstract class DataBaseHelper : RoomDatabase() {
    abstract fun contactsDao(): ContactsDao

    companion object {
        private var instance: DataBaseHelper? = null

        @Synchronized
        fun getInstance(context: Context): DataBaseHelper? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseHelper::class.java,
                    "database"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}
