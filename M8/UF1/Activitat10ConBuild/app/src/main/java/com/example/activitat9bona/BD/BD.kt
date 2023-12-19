package com.example.activitat9bona.BD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = arrayOf(Usuari::class, Carritos::class),
    version = 1
)

abstract class BD: RoomDatabase() {
    abstract fun daoUsuari():DaoUsuari
    abstract fun daoCarrito():DaoCarrito

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