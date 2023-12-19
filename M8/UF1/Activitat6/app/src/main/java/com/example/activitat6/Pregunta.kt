package com.example.activitat6


data class Pregunta(
    var pregunta:String,
    var resposta1:String,
    var resposta2:String,
    var resposta3:String,
    var correcta:Int,
    var respostaUsuari:Int?
)

lateinit var preguntes: ArrayList<Pregunta>