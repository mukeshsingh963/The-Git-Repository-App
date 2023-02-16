package com.learning.thegitrepoapp.jetpack.module

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.learning.thegitrepoapp.jetpack.Dao.AppDao
import com.learning.thegitrepoapp.jetpack.model.ItemModel

@Database(entities =arrayOf(ItemModel::class), version = 1, exportSchema = false)


abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao() : AppDao


    companion object{
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class.java) {
                    instance = get(context)
                }
            }
            return instance!!
        }
        fun get(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "test"
            ).build()
        }
    }
}