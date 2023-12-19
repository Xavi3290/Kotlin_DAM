package com.example.webservice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.webservice2.databinding.ActivityMainBinding
import com.example.webservice2.dataclasses.BreedsCatsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: Adaptador

    private var llista1 = mutableListOf<BreedsCatsResponse>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initRecyclerView()
        searchCatsBreeds()
    }

    private fun initRecyclerView(){
        adapter = Adaptador(llista1)
        binding.rv1.layoutManager = GridLayoutManager(this,2)
        binding.rv1.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.thecatapi.com/v1/breeds/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun searchCatsBreeds(){
        CoroutineScope(Dispatchers.IO).launch {
            val url = "?api_key="+getString(R.string.apikey)
            val call = getRetrofit().create(APIService::class.java).getBreedsCats(url)
            val breeds = call.body()
            runOnUiThread {
                llista1 = breeds as MutableList<BreedsCatsResponse>
                adapter = Adaptador(llista1)
                binding.rv1.layoutManager = GridLayoutManager(binding.rv1.context,2)
                binding.rv1.adapter = adapter
            }
        }
    }
}