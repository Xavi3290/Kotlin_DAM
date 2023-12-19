package com.example.pr1xavierroca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pr1xavierroca.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usuari = intent.getStringExtra("usuari")
        val password = intent.getStringExtra("pass")

        binding.etUsuari.setText(usuari)
        binding.etPass.setText(password)


        binding.bLogin.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("usuari",binding.etUsuari.text.toString())
            intent.putExtra("pass",binding.etPass.text.toString())
            startActivity(intent)
        }

        binding.bRegister.setOnClickListener {
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
        }

    }
}