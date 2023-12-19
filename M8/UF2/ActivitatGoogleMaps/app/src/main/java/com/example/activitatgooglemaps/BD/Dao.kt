package com.example.activitatgooglemaps.BD

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoUbicacio {
    @Insert
    suspend fun afegir(ubicacio: Ubicacio)

    @Query("select * from ubi")
    suspend fun selectAll(): List<Ubicacio>

    @Query("select * from ubi where id=:id")
    suspend fun selectUbicacio(id: Int): List<Ubicacio>
}