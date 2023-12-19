package com.example.exaxavierroca.BD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "esde")
data class Esdeveniment(
    @PrimaryKey(autoGenerate = true)
    val codi: Int?,
    @ColumnInfo(index = true)  // index= true tindra un index per busca rapida, para hacer filtraciones de un campo
    val descripcio: String,
    val data: String
)