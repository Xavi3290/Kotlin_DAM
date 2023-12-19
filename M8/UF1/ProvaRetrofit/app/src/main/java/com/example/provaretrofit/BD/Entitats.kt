package com.example.provaretrofit.BD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuaris")
data class Usuari(
    @PrimaryKey(autoGenerate = true)
    val codi: Int?,
    @ColumnInfo(index = true)  // index= true tindra un index per busca rapida, para hacer filtraciones de un campo
    val nom: String,
    val password: String
)