package com.example.exemplebdromm.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(Empleat::class, Departament::class),
    version = 2
)
abstract class BD: RoomDatabase() {
    abstract fun daoEmpleats():DaoEmpleats
    abstract fun daoDepartaments():DaoDepartaments

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
                    BD::class.java, "empresa"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}