package com.example.activitat6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activitat6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.comencar.setOnClickListener {
            val usuari = binding.nomalumne.text.toString()
            val intent = Intent(this, PreguntesActivity::class.java)
            intent.putExtra("nomalumne", usuari)
            startActivity(intent)
        }
    }

}