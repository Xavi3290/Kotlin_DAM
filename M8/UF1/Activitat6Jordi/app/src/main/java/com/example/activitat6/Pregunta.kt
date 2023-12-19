package com.example.activitat6

data class Pregunta(
    val pregunta: String,
    val tipus: String,
    val opcio1: String?,
    val opcio2: String?,
    val opcio3: String?,
    val correcta: String,
    var resposta: String?
)

lateinit var preguntes: java.util.ArrayList<Pregunta>

fun omplirPreguntes(){
    preguntes = arrayListOf()
    preguntes.add(
        Pregunta(
            "Com es diu l'institut?",
            "directa",
            null,
            null,
            null,
            "Carles Vallbona",
            null
        )
    )
    preguntes.add(
        Pregunta(
            "De quin color és el logo de l'institut?",
            "opcions",
            "Verd i blau",
            "Taronja i groc",
            "Vermell i blanc",
            "Vermell i blanc",
            null
        )
    )
    preguntes.add(
        Pregunta(
            "El tutor del grup es diu Miquel?",
            "logica",
            null,
            null,
            null,
            "Cert",
            null
        )
    )
    preguntes.add(
        Pregunta(
            "Com es diu el professor de M8?",
            "directa",
            null,
            null,
            null,
            "Jordi",
            null
        )
    )
    preguntes.add(
        Pregunta(
            "Quantes UFs té M8?",
            "opcions",
            "2",
            "3",
            "4",
            "3",
            null
        )
    )
}