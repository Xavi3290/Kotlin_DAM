package com.example.activitat10.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DaoUsuari {
    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("select contra from User where nomusu=:nomu")
    suspend fun getPw(nomu: String): List<String>

    @Query("select tipus from User where nomusu=:nomu")
    suspend fun getTipus(nomu: String): List<String>
}

@Dao
interface DaoProducteCarret {
    @Insert
    suspend fun insertProducteCarret(producteCarret: ProducteCarret)

    @Update
    suspend fun updateProducteCarret(producteCarret: ProducteCarret)

    @Delete
    suspend fun deleteProducteCarret(producteCarret: ProducteCarret)

    @Query("select * from ProducteCarret where nomusu=:nomu")
    suspend fun selectAllProducteCarret(nomu: String):List<ProducteCarret>
}