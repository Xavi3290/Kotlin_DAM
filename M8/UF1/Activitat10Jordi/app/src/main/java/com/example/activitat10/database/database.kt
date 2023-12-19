package com.example.activitat10.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(User::class, ProducteCarret::class),
    version = 1
)
abstract class database: RoomDatabase() {
    abstract fun DaoUsers() : DaoUsuari
    abstract fun DaoProductes() : DaoProducteCarret

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
                    database::class.java, "activitat10"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}