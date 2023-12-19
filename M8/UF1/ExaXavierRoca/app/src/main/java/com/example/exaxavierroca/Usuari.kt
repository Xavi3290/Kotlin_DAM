package com.example.exaxavierroca

import com.example.exaxavierroca.BD.Esdeveniment


var listEsdeveniments:ArrayList<Esdeveniment> = arrayListOf()


data class Usuari (
    val usuari:String,
    val password:String,
    val tipus:String,
    val imatge:String
)

data class Usuari2(
    val usuari:String,
    val password:String,
    val tipus:String,
)

data class ResponseModel2(
    val resposta:String,
    val imatge:String
)

data class ResponseModel1(
    val resposta:String
)