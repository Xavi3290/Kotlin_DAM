package com.marjasoft.ejcamerax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.marjasoft.ejcamerax.databinding.ActivityFotoGalleryBinding

class FotoGallery : AppCompatActivity() {
    private lateinit var binding : ActivityFotoGalleryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFotoGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var url = intent.getStringExtra("url").toString()
        var descripcio = intent.getStringExtra("descripcio").toString()
        var datahora = intent.getStringExtra("datahora").toString()

        binding.tvDescripcio.setText(descripcio)
        binding.tvDataHora.setText("Data i hora: "+datahora)
        Glide.with(this).load(url).into(binding.ivGallery)
    }
}