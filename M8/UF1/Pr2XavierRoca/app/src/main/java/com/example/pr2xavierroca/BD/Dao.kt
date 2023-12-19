package com.example.pr2xavierroca.BD

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@androidx.room.Dao
interface DaoSerie {

    @Query("select * from series")
    suspend fun selectSeries(): List<Serie>

    @Insert
    suspend fun afegirSerie(serie: Serie)

    @Update
    suspend fun modificarSerie(serie: Serie)

    @Delete
    suspend fun esborraSerie(serie: Serie)
}

@androidx.room.Dao
interface DaoCapitol {

    @Query("select * from capitols")
    suspend fun selectCapitols(): List<Capitol>

    @Insert
    suspend fun afegirCapitol(capitol: Capitol)
}