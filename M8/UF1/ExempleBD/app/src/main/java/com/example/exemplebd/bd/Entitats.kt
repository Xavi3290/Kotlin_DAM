package com.example.exemplebd.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "pelicules")
data class Pelicula(
    @PrimaryKey(autoGenerate = true)
    var codi: Int?,
    @ColumnInfo(index = true)  // index= true tindra un index per busca rapida, para hacer filtraciones de un campo
    var titol: String,
    var dataEstrena: String
    )

