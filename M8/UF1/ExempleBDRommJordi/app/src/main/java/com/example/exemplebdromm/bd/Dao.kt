package com.example.exemplebdromm.bd

import androidx.room.Query
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface DaoEmpleats {
    @Query("select * from empleats")
    suspend fun llistarEmpleats():List<Empleat>

    @Query("select nomempleat from empleats")
    suspend fun llistaNomsEmpleats():List<String>

    @Query("select * from empleats where nomempleat=:nom")
    suspend fun getEmpleat(nom: String): Empleat

    @Insert
    suspend fun afegeixEmpleat(empleat: Empleat)

    @Update
    suspend fun modificaEmpleat(empleat: Empleat)

    @Delete
    suspend fun esborraEmpleat(empleat: Empleat)

    @Query("update empleats set nomempleat=:nom where codi=:codi")
    suspend fun modificaNomEmpleat(nom: String, codi: Int)
}

@Dao
interface DaoDepartaments {
    @Insert
    suspend fun afegeixDepartament(departament: Departament)

    @Query("select * from  departaments")
    suspend fun getAllDepartaments(): List<Departament>

    @Update
    suspend fun modificaDepartament(departament: Departament)

    @Delete
    suspend fun esborraDepartament(departament: Departament)
}