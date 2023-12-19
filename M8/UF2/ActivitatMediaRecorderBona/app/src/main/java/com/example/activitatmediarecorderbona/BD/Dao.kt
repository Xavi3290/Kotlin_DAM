package com.example.activitatmediarecorderbona.BD

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@androidx.room.Dao
interface DaoGrabacions {

    @Query("select * from grabacions")
    suspend fun selectGrabacions(): List<Grabacio>

    @Insert
    suspend fun afegirGrabacio(esdeveniment: Grabacio)

    @Delete
    suspend fun esborraGrabacio(esdeveniment: Grabacio)
}