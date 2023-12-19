package com.example.activitatmediarecorder.BD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(Grabacio::class),
    version = 1
)

abstract class BD: RoomDatabase() {
    abstract fun daoGrabacions():DaoGrabacions

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
                    BD::class.java, "grabacions"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}