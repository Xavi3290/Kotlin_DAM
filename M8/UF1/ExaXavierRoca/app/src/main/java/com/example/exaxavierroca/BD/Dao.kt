package com.example.exaxavierroca.BD

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@androidx.room.Dao
interface DaoEsdeveniment {

    @Query("select * from esde")
    suspend fun selectEsdeveniments(): List<Esdeveniment>

    @Insert
    suspend fun afegirEsdeveniment(esdeveniment: Esdeveniment)

    @Delete
    suspend fun esborraEsdeveniment(esdeveniment: Esdeveniment)
}