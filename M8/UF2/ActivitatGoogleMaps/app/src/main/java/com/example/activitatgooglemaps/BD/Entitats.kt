package com.example.activitatgooglemaps.BD

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "ubi")
data class Ubicacio (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val descripcio: String,
    val latitud: Double,
    val longitud: Double

)

var listubicacions: ArrayList<Ubicacio> = arrayListOf()
var listseleccio: ArrayList<Ubicacio> = arrayListOf()