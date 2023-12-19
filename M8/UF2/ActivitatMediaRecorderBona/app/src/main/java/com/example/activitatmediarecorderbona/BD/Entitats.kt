package com.example.activitatmediarecorderbona.BD

import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.room.Entity
import androidx.room.PrimaryKey

val record_audio_request_code = 0
var acceptat = 0
var mediaPlayer: MediaPlayer? = null

@Entity(tableName = "grabacions")
data class Grabacio(
    @PrimaryKey()
    val nom: String,
    val direccio: String?,
)