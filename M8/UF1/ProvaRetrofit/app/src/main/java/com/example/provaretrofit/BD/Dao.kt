package com.example.provaretrofit.BD

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@androidx.room.Dao
interface DaoUsuari {

    @Query("select * from usuaris where nom=:nom")
    suspend fun selectUsuari(nom: String): List<Usuari>

    @Insert
    suspend fun afegirUsuari(usuari: Usuari)

    @Update
    suspend fun modificarUsuari(usuari: Usuari)

    @Delete
    suspend fun esborraUsuari(usuari: Usuari)
}