package com.example.webservice2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.webservice2.databinding.ActivityBreedDetailsBinding
import com.example.webservice2.dataclasses.BreedsCatsResponse
import com.example.webservice2.dataclasses.ImageCatsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BreedDetails : AppCompatActivity() {
    private lateinit var binding: ActivityBreedDetailsBinding
    private lateinit var adapter: Adaptador2
    private var llista = mutableListOf<ImageCatsResponse>()
    private lateinit var breed_id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBreedDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        breed_id = intent.getStringExtra("id")!!
        initRecyclerView()
        searchImageCats()
    }

    private fun initRecyclerView() {
        adapter = Adaptador2(llista)
        binding.rv2.layoutManager = LinearLayoutManager(this)
        binding.rv2.adapter=adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.thecatapi.com/v1/images/search/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun searchImageCats(){
        CoroutineScope(Dispatchers.IO).launch {
            val url = "?breed_ids="+breed_id+"&limit=10&api_key="+getString(R.string.apikey)
            val call = getRetrofit().create(APIService::class.java).getImageBreedsCats(url)
            val breeds = call.body()
            runOnUiThread {
                llista = breeds as MutableList<ImageCatsResponse>
                with(llista.get(0).breeds.get(0)) {
                    binding.breedName.text = name
                    binding.adapt.rating = adaptability!!.toFloat()
                    binding.affect.rating = affectionLevel!!.toFloat()
                    binding.cfaurl.text = cfaUrl.toString()
                    binding.childfriendly.rating = childFriendly!!.toFloat()
                    binding.description.text = description
                    binding.dogfriendly.rating = dogFriendly!!.toFloat()
                    binding.temperament.text = temperament
                    binding.energylevel.rating = energyLevel!!.toFloat()

                    binding.cfaurl.setOnClickListener {
                        val url = cfaUrl.toString()
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    }
                }
                adapter = Adaptador2(llista)
                binding.rv2.layoutManager = LinearLayoutManager(binding.rv2.context)
                binding.rv2.adapter = adapter
            }
        }
    }
}