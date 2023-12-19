package com.example.activitat7_grupa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activitat7_grupa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.accedeix.setOnClickListener {
            val nomusuari = binding.nomusua.text.toString()
            val password = binding.password.text.toString()

            val intent = Intent(this, AssignaturesActivity::class.java)
            intent.putExtra("nomusuari", nomusuari)
            intent.putExtra("password", password)
            startActivity(intent)
        }
    }
}