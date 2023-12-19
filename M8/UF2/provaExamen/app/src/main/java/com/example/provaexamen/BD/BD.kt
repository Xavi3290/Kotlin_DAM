package com.example.provaexamen.BD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(Grabacio::class,Media::class,Ubicacio::class),
    version = 1
)

abstract class BD: RoomDatabase() {
    abstract fun daoGrabacions():DaoGrabacions
    abstract fun daoMedia():DaoMedia
    abstract fun daoubicacions():DaoUbicacio

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
                    BD::class.java, "provaExamen"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}