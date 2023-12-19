package com.example.exaxavierroca

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.exaxavierroca.databinding.ActivityMostrarTotBinding
import java.io.IOException

class MostrarTotActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMostrarTotBinding
    var fragment: FragmentMap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarTotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val posicio = intent.getIntExtra("position",0)

        binding.bPlay.setOnClickListener {
            playAudio(binding.bPlay.context, listGrabacio[posicio].direccio)
        }

        binding.bStop.setOnClickListener {
            stopSound()
        }

        Glide.with(binding.ivGallery.context).load(llistFoto[posicio].url)
            .into(binding.ivGallery)


        fragment = FragmentMap()

        val args = Bundle()
        args.putInt("origen", posicio)
        fragment!!.arguments = args

        supportFragmentManager
            .beginTransaction().replace(R.id.fcview, fragment!!)
            .commit()



    }

    private fun playAudio(context: Context, direc:String?) {
        //val direc = context.getExternalFilesDir(null)!!.absolutePath + "/recording.mp3"
        val url = Uri.parse(direc)
        playContentUri(url,context)
    }

    fun playContentUri(uri: Uri, context: Context) {
        try {
            if (mediaPlayer != null){
                mediaPlayer!!.stop()
                mediaPlayer!!.release()
            }
            mediaPlayer = MediaPlayer().apply {
                setDataSource(context, uri)
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                prepare()
                start()
            }
        } catch (exception: IOException) {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    fun stopSound() {
        if (mediaPlayer != null) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }


}