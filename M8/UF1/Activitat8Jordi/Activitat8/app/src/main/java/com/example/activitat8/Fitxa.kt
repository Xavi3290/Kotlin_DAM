package com.example.activitat8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.activitat8.databinding.ActivityFitxaBinding

class Fitxa : AppCompatActivity() {
    private lateinit var binding: ActivityFitxaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFitxaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hora = intent.getIntExtra("hora", 0)
        binding.HumitatFitxa.setText("Humitat: " + dades[hora].humitat + "%")
        binding.VentFitxa.setText("Velocitat Vent: " + dades[hora].vent + "Km/h")
        binding.PrecipitacioFitxa.setText("Precipitació: " + dades[hora].precipitacio)
        binding.temperaturaFitxa.setText(dades[hora].graus.toString() + "ºC")
        Glide.with(this).load(arrel_url + "256" + dades[hora].url).into(binding.imageViewFitxa)
    }
}