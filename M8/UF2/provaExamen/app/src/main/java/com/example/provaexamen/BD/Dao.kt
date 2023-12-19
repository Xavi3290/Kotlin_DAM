package com.example.provaexamen.BD

import androidx.room.*

@androidx.room.Dao
interface DaoGrabacions {

    @Query("select * from grabacions")
    suspend fun selectGrabacions(): List<Grabacio>

    @Insert
    suspend fun afegirGrabacio(esdeveniment: Grabacio)

    @Delete
    suspend fun esborraGrabacio(esdeveniment: Grabacio)
}

@androidx.room.Dao
interface DaoMedia {
    @Insert
    suspend fun afegir(media: Media)

    @Query("select * from media")
    suspend fun selectAll(): List<Media>
}

@androidx.room.Dao
interface DaoUbicacio {
    @Insert
    suspend fun afegeixUbicacio(ubicacio: Ubicacio)

    @Query("select * from ubicacio")
    suspend fun selectAll(): List<Ubicacio>
}