package com.example.activitat9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitat9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var dadesPortada : java.util.ArrayList<portada>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        omplirDadesPortada()


        binding.rvportada.layoutManager = LinearLayoutManager(this)
        binding.rvportada.adapter = PortadaAdapter(dadesPortada)
    }

    private fun omplirDadesPortada() {
        dadesPortada = arrayListOf()
        dadesPortada.addAll(
            listOf(
                portada("https://cms-images.mmst.eu/osyynfyvlyjc/2Bf1asULGycgHN9oP5cOI5/d9f769907663aae42b34cae95b6e194e/test_1.png", "Smartphones"),
                portada("https://cms-images.mmst.eu/osyynfyvlyjc/3cIeQiIHcJJUk2s2yPFP2x/158640ac43949b00e877e240c9938e2f/portatil1.png", "Ordinadors"),
                portada("https://cms-images.mmst.eu/osyynfyvlyjc/3SiL1Mo8WNRMMTwADtoHJH/54312a5006894bda0e14f4c207025a99/tele1.png", "Televisions"),
                portada("https://cms-images.mmst.eu/osyynfyvlyjc/4bril4wujHiHuI7ugmjyEH/80866060e6e05242018fc6f993a8477c/patinete.png", "Movilitat")
            )
        )

    }

}