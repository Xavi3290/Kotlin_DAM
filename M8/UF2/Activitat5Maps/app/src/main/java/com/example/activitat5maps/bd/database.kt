package com.example.activitat5maps.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(Ubicacio::class),
    version = 1
)
abstract class database: RoomDatabase() {
    abstract fun DaoUbicacions() : Dao

    companion object {
        @Volatile
        private var INSTANCE : database? = null

        fun getDatabase(context: Context): database {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return  tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    database::class.java, "ubicacions"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}