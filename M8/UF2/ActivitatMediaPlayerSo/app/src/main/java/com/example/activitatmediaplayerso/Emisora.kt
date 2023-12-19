package com.example.activitatmediaplayerso


var listEmisores:ArrayList<Emisora> = arrayListOf()



data class Emisora(
    val url:String,
    val nom:String
)

fun omplirEmisores(){
    listEmisores.addAll(
        listOf(
            Emisora("https://21633.live.streamtheworld.com/RAC_1_SC?dist=web","Rac1"),
            Emisora("https://nodo05-cloud01.streaming-pro.com:8005/flaixbac.mp3","FlaixBac"),
            Emisora("https://flaixfm.streaming-pro.com:8001/flaixfm.mp3","FlaixFm"),
            Emisora("https://playerservices.streamtheworld.com/api/livestream-redirect/RAC105.mp3","Rac105"),
            Emisora("https://dhits.frilab.com:8443/dhits","Dhits")
        )
    )
}