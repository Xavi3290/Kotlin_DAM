package com.marjasoft.ejcamerax

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.marjasoft.ejcamerax.databinding.ActivityVideoGalleryBinding

class VideoGallery : AppCompatActivity() {
    private lateinit var binding: ActivityVideoGalleryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var url = intent.getStringExtra("url").toString()
        var descripcio = intent.getStringExtra("descripcio").toString()
        var datahora = intent.getStringExtra("datahora").toString()

        binding.tvDescripcio.setText(descripcio)
        binding.tvDataHora.setText("Data i hora: "+datahora)

        var mediaControls = MediaController(this)
        mediaControls.setAnchorView(binding.vvGallery)
        binding.vvGallery.setMediaController(mediaControls)
        binding.vvGallery.setVideoURI(Uri.parse(url))

    }
}