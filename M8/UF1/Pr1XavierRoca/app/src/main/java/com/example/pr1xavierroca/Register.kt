package com.example.pr1xavierroca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pr1xavierroca.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bRegister.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            intent.putExtra("usuari",binding.etUsuari.text.toString())
            val pass1 = binding.etPass.text.toString()
            val pass2 = binding.etPassConf.text.toString()
            if(pass1 == pass2){
                intent.putExtra("pass",binding.etPass.text.toString())
                startActivity(intent)
            }else{
                Toast.makeText(this,"Els pass no coincideixen", Toast.LENGTH_LONG).show()
            }
        }

    }
}