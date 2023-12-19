package com.example.exaxavierroca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exaxavierroca.databinding.ActivityLlistaBinding

class LlistaActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLlistaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLlistaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rv1.adapter = AdaptadorFoto(llistFoto)
        binding.rv1.layoutManager = LinearLayoutManager(this)



    }
}