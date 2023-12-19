package com.marjasoft.ejcamerax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.marjasoft.ejcamerax.databinding.ActivityCamaraBinding
import com.marjasoft.ejcamerax.databinding.ActivityFotoGalleryBinding

class FotoGallery : AppCompatActivity() {
    private lateinit var binding : ActivityFotoGalleryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFotoGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var url = intent.getStringExtra("urlfoto").toString()

        Log.i("url", url)
        Glide.with(this).load(url).into(binding.ivGallery)
    }
}