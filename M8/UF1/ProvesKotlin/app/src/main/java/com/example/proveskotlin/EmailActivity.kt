package com.example.proveskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proveskotlin.databinding.ActivityEmailBinding

class EmailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEmailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}