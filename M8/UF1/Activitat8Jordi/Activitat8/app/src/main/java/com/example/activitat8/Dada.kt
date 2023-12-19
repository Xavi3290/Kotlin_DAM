package com.example.activitat8

val arrel_url = "https://ssl.gstatic.com/onebox/weather/"
val partly_cloudy = "/partly_cloudy.png"
val sunny = "/sunny.png"
val cloudy = "/cloudy.png"
val rain_cloudy = "/rain_s_cloudy.png"
val cloudy_rain = "/cloudy_s_rain.png"
val sunny_rain = "/sunny_s_rain.png"
val rain_light = "/rain_light.png"
val rain = "/rain.png"
val snow_rain = "/snow_s_rain.png"
val snow_sunny = "/snow_s_sunny.png"
val snow ="/snow.png"

data class Dada (
    val hora: Int,
    val url : String,
    val graus : Int,
    val precipitacio: Int,
    val humitat: Int,
    val vent: Int
)

var dades = ArrayList<Dada>()

fun omplirDades(){
    dades.addAll(
        listOf(
            Dada(0, snow_rain, 3, 6, 80, 11),
            Dada(1, snow, 3, 4, 80, 10),
            Dada(2, snow_sunny, 3, 1, 75, 9),
            Dada(3, cloudy, 2, 0, 75, 5),
            Dada(4, cloudy, 2, 0, 75, 5),
            Dada(5, partly_cloudy, 3, 0, 70, 3),
            Dada(6, partly_cloudy, 4, 0, 65, 3),
            Dada(7, sunny_rain, 4, 0, 70, 5)
        )
    )
}



