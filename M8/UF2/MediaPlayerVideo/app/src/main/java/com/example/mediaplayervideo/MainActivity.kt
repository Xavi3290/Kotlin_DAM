package com.example.mediaplayervideo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import com.example.mediaplayervideo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // El vídeo es mostrarà al VideoView.
    var videoView: VideoView? = null

    // Són els controls del vídeo
    var mediaControls: MediaController? = null

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Agafo el videoView de la vista i instancio els controls
        videoView = binding.videoView1
        mediaControls = MediaController(this)
        // Indico que el video i els controls estan associats.
        mediaControls!!.setAnchorView(videoView)
        videoView!!.setMediaController(mediaControls)

        // Indico la URL del vídeo (en aquest cas un recurs)
        videoView!!.setVideoURI(
            Uri.parse("android.resource://"
                + packageName + "/" + R.raw.spreedsheet_odoo16))

        // Indico que el video és qui té el focus de tot el layout.
        videoView!!.requestFocus()

        videoView!!.start()

        // S'executa quan el vídeo finalitza
        videoView!!.setOnCompletionListener { 
            Toast.makeText(this, "Vídeo finalitzat", Toast.LENGTH_LONG).show()
        }

        //videoView!!.setOnErrorListener { mp, what, extra ->  }   //Per errors

        //videoView!!.setOnPreparedListener {  }   //preparat per executarse

    }
}