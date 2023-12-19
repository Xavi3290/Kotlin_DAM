package com.example.exemplebd.bd

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@androidx.room.Dao   //@Dao , sale esto o haces el import
interface Dao {

    @Query("select * from pelicules")
    suspend fun selectAllPelicules(): List<Pelicula>

    @Insert
    suspend fun afegirPelicula(pelicula: Pelicula)

    @Update
    suspend fun modificarPelicula(pelicula: Pelicula)

    @Delete
    suspend fun esborrarPelicula(pelicula: Pelicula)

    @Query("update pelicules set titol=:titol where codi=:codi")
    suspend fun modificarPeliculaPelTiol(titol:String,codi:Int)
}