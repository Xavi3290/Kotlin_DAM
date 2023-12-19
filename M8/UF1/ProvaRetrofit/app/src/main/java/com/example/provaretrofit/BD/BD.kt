package com.example.provaretrofit.BD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(Usuari::class),
    version = 1
)

abstract class BD: RoomDatabase() {
    abstract fun daoUsuari():DaoUsuari

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
                    BD::class.java, "tienda"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}