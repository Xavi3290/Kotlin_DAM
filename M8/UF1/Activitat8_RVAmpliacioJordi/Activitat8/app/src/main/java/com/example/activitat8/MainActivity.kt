package com.example.activitat8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitat8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adaptador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        omplirDades()

        //Es dispara cada vegada que escribim una lletra en l'edittexte
        //Per filtrar
        binding.tvFiltrar.addTextChangedListener { filtre ->
            val novesdades = dades.filter {
                    Dada -> Dada.hora.toString().contains(filtre.toString())
            } as ArrayList<Dada>
            adapter.updateDades(novesdades)
        }

        adapter = Adaptador(dades)
        binding.rv1.adapter = adapter
        binding.rv1.layoutManager = LinearLayoutManager(this)
    }
}