package com.example.activitatgooglemaps.BD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(Ubicacio::class),
    version = 1
)

abstract class BD: RoomDatabase() {
    abstract fun daoUbicacio():DaoUbicacio

    companion object {
        @Volatile
        private var INSTANCE : BD? = null

        fun getDatabase(context: Context): BD {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return  tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BD::class.java, "Map"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}