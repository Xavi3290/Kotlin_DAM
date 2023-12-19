package com.example.provaretrofit

import com.example.provaretrofit.BD.Usuari
import com.google.gson.annotations.SerializedName

var userBD: List<Usuari>? = arrayListOf()
var listProducte: ArrayList<Producte> = arrayListOf()


data class Producte (
    val codi: Int?,
    val imatge: String,
    val descripcio: String,
    val descripcioCompleta: String,
    val preu: Double,
    val tipusProducte: Int,
    val tipusCardview: Int
)

data class ResponseModel(
    var missatge : String? = null
)