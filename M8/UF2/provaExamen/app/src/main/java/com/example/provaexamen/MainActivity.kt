package com.example.provaexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.provaexamen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bEmisora.setOnClickListener {
            val intent = Intent(this, EmisoraActivity::class.java)
            startActivity(intent)
        }
        binding.bVideo.setOnClickListener {
            val intent = Intent(this, VideoActivity::class.java)
            startActivity(intent)
        }
        binding.bGrabacio.setOnClickListener {
            val intent = Intent(this, GrabacioActivity::class.java)
            startActivity(intent)
        }
        binding.bCamara.setOnClickListener {
            val intent = Intent(this, camara::class.java)
            startActivity(intent)
        }
        binding.bMap.setOnClickListener {
            val intent = Intent(this, LocaliztacioActivity::class.java)
            startActivity(intent)
        }

    }
}