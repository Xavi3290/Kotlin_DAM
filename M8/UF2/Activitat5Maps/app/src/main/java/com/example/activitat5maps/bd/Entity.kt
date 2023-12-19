package com.example.activitat5maps.bd

import androidx.room.PrimaryKey

@androidx.room.Entity
data class Ubicacio (
    @PrimaryKey
    var id: Int?,
    var ubicacio: String,
    var latitud: Double,
    var longitud: Double,
    var tipuscard: Int
    )

lateinit var dades : List<Ubicacio>