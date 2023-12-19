package com.example.activitat7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activitat7.databinding.ActivityPrincipalBinding

class Principal : AppCompatActivity() {
    private lateinit var binding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bStart.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("usuari",binding.etUsuari.text.toString())
            intent.putExtra("pass",binding.etPass.text.toString())
            startActivity(intent)
        }
    }
}