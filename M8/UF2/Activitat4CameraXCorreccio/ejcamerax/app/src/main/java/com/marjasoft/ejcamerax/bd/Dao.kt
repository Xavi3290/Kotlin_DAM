package com.marjasoft.ejcamerax.bd

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Dao

@Dao
interface Dao {
    @Insert
    suspend fun afegir(media: Media)

    @Query("select * from Media")
    suspend fun selectAll(): List<Media>
}