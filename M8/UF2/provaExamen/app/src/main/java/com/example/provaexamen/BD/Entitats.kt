package com.example.provaexamen.BD

import android.media.MediaPlayer
import androidx.room.Entity
import androidx.room.PrimaryKey


var mediaPlayer: MediaPlayer? = null

@Entity(tableName = "grabacions")
data class Grabacio(
    @PrimaryKey()
    val id:Int?,
    val nom: String,
    val direccio: String?,
)


@Entity (tableName = "media")
data class Media (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val descripcio: String,
    val url: String,
    val tipus: String,
    val datahora: String
)


@Entity (tableName = "ubicacio")
data class Ubicacio (
    @PrimaryKey
    val id: Int?,
    val descripcio: String,
    val latitud: Double,
    val longitud: Double
)

lateinit var dades : List<Ubicacio>
var listubicacions: ArrayList<Ubicacio> = arrayListOf()
var listseleccio: ArrayList<Ubicacio> = arrayListOf()
var metode = "Cotxe"
var cont = 0

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


var listVideos:ArrayList<Video> = arrayListOf()

class Video (
    val nom:String,
    val url:String
)

fun omplirVideo(){
    listVideos.addAll(
        listOf(
            Video("Elephant Dream","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"),
            Video("Big Buck Bunny","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
            Video("For Bigger Blazes","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"),
        )
    )
}