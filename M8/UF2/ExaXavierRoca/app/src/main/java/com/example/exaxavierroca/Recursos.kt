package com.example.exaxavierroca

import android.media.MediaPlayer


var mediaPlayer: MediaPlayer? = null


data class Grabacio(
    val id:Int?,
    val direccio: String?,
)

data class Media(
    val id: Int?,
    val url: String
)

data class Ubicacio(
    val id: Int?,
    val latitud: Double,
    val longitud: Double
)

var listGrabacio: ArrayList<Grabacio> = arrayListOf()
var listUbicacions: ArrayList<Ubicacio> = arrayListOf()
var llistFoto:ArrayList<Media> = arrayListOf()



