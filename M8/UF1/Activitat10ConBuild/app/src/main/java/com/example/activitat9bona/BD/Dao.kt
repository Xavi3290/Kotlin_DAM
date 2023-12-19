package com.example.activitat9bona.BD

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

@androidx.room.Dao
interface DaoCarrito {

    @Query("select * from carritos where codiUsuari=:codiUsuari and codiProducte=:codiProducte")
    suspend fun selectCarritosProducte(codiUsuari:Int?,codiProducte:Int):Carritos  //puede que falle

    /*@Query("select * from carritos where codiUsuari=:codiUsuari")
    suspend fun selectCarritos(codiUsuari:Int?):ArrayList<Carritos>*/

    @Insert
    suspend fun afegirCarrito(carrito: Carritos)

    @Update
    suspend fun modificarCarrito(carrito: Carritos)

    @Delete
    suspend fun esborrarCarrito(carrito: Carritos)

}