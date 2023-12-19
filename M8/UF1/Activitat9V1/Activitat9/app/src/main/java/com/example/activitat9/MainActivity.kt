package com.example.activitat9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitat9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        omplirPortada()

        llencarPortada()

    }

    private fun llencarPortada() {
        binding.rv1.layoutManager = LinearLayoutManager(this)
        binding.rv1.adapter = AdaptadorPortada(listPortada)
    }
}