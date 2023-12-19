package com.example.retrofit1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit1.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        getMethod()
    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://dummy.restapiexample.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getMethod() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit().create(APIService::class.java).getEmployees().body()
            val dades = response!!.data
            runOnUiThread {
                val adaptador = Adaptador(dades)
                binding.rv1.adapter = adaptador
                binding.rv1.layoutManager = LinearLayoutManager(binding.rv1.context)
            }


        }

    }
}