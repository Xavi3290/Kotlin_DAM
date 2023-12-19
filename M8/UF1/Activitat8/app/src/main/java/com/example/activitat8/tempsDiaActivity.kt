package com.example.activitat8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.activitat8.databinding.ActivityTempsDiaBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class tempsDiaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTempsDiaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTempsDiaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val img = intent.getStringExtra("img").toString()
        val hora = intent.getStringExtra("hora").toString()
        val temp = intent.getStringExtra("temperatura").toString()
        val prep = intent.getStringExtra("precipitacio").toString()
        val humitat = intent.getStringExtra("humitat").toString()
        val vent = intent.getStringExtra("vent").toString()

        val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val dia = LocalDateTime.now()
        val diaEuropeu = dia.format(format)


        Glide.with(this).load(img).into(binding.ivImatge)
        binding.tvHora.setText(hora)
        binding.tvTemp.setText(temp)
        binding.tvPrecipitacio.setText(prep)
        binding.tvHumitat.setText(humitat)
        binding.tvVent.setText(vent)
        binding.tvDia.setText(diaEuropeu.toString())

        binding.bTornar.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}