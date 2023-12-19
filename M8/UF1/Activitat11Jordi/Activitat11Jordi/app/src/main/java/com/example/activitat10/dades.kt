package com.example.activitat10

import androidx.navigation.NavController
import com.example.activitat10.database.ProducteCarret
import com.google.android.material.navigation.NavigationView
import com.google.gson.annotations.SerializedName

data class portada(
    val imatge: String,
    val descripcio: String
)

data class Producte(
    val codi: Int?,
    val imatge: String,
    val descripcio: String,
    val descripcioCompleta: String,
    val preu: Double,
    val tipusProducte: Int,
    val tipusCardview: Int
)

class Productes : ArrayList<Producte>()

lateinit var usuariActual : String
lateinit var tipusUsuari : String
lateinit var dadesPortada : ArrayList<portada>
lateinit var llistaProductes: Productes
var llistaCarret: ArrayList<ProducteCarret> = arrayListOf()

data class ResponseModel(
    @SerializedName("missatge") var missatge : String? = null
)

lateinit var navView: NavigationView
lateinit var navController : NavController


fun omplirDadesPortada() {
    dadesPortada = arrayListOf()
    dadesPortada.addAll(
        listOf(
            portada("https://cms-images.mmst.eu/osyynfyvlyjc/2Bf1asULGycgHN9oP5cOI5/d9f769907663aae42b34cae95b6e194e/test_1.png", "Smartphones"),
            portada("https://cms-images.mmst.eu/osyynfyvlyjc/3cIeQiIHcJJUk2s2yPFP2x/158640ac43949b00e877e240c9938e2f/portatil1.png", "Ordinadors"),
            portada("https://cms-images.mmst.eu/osyynfyvlyjc/3SiL1Mo8WNRMMTwADtoHJH/54312a5006894bda0e14f4c207025a99/tele1.png", "Televisions"),
            portada("https://cms-images.mmst.eu/osyynfyvlyjc/4bril4wujHiHuI7ugmjyEH/80866060e6e05242018fc6f993a8477c/patinete.png", "Movilitat")
        )
    )

}