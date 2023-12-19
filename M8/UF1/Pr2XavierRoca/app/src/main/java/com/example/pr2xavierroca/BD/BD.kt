package com.example.pr2xavierroca.BD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = arrayOf(Serie::class, Capitol::class),
    version = 1
)

abstract class BD: RoomDatabase() {
    abstract fun daoSerie():DaoSerie
    abstract fun daoCapitol():DaoCapitol

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
                    BD::class.java, "Examen"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}
