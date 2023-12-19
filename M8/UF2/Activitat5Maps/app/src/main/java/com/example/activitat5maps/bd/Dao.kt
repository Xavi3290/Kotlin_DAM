package com.example.activitat5maps.bd

import androidx.room.Insert
import androidx.room.Query

@androidx.room.Dao
interface Dao {
    @Insert
    suspend fun afegeixUbicacio(ubicacio: Ubicacio)

    @Query("select * from Ubicacio")
    suspend fun selectAll(): List<Ubicacio>
}