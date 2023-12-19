package com.example.activitat9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.activitat9.databinding.ActivityFitxaProducteBinding

class FitxaProducte : AppCompatActivity() {
    private lateinit var binding: ActivityFitxaProducteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFitxaProducteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imatge = intent.getStringExtra("img").toString()
        val descripcio = intent.getStringExtra("descripcio").toString()
        val descripcioCompleta = intent.getStringExtra("descripcioCompleta").toString()
        val preu = intent.getStringExtra("preu").toString()
        val codi = intent.getIntExtra("codi", 0)

        Glide.with(this).load(imatge).into(binding.ivImatge)
        binding.tvDescripcio.setText(descripcio)
        binding.tvDescripcioCompleta.setText(descripcioCompleta)
        binding.tvPreu.setText(preu)





    }
}