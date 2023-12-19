package com.example.exemplebd.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(Pelicula::class),
    version = 1
)
abstract class BdPelicules(): RoomDatabase() {
    abstract fun Dao(): Dao

    companion object{     //aixo en cada Dao
        @Volatile
        private var INSTANCE: BdPelicules? = null

        fun getDatabase(context: Context): BdPelicules{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BdPelicules::class.java,"pelicules"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}