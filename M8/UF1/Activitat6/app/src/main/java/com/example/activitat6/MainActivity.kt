package com.example.activitat6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activitat6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bSart.setOnClickListener {
            val intent = Intent(this, PreguntesActivity::class.java)
            intent.putExtra("nom",binding.etNom.text.toString())
            startActivity(intent)
        }
    }


}