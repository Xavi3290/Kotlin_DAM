package com.example.activitat9bona

data class Producte_JsonItem(
    val codi: Int,
    val descripcio: String,
    val descripcioCompleta: String,
    val imatge: String,
    val preu: Double,
    val tipusCardview: Int,
    val tipusProducte: Int
)