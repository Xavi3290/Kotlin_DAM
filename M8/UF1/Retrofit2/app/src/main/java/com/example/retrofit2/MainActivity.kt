package com.example.retrofit2

import ProductesItem
import ResponseModel

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit2.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        binding.btgetall.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val response = getRetrofit().create(APIService::class.java).getProductes().body()
                val dades = response!!

                runOnUiThread {
                    Toast.makeText(
                        this@MainActivity,
                        "S'han recuperat " + dades.size + " valors",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        binding.btgettipus.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val tipus = "1"
                val response = getRetrofit().create(APIService::class.java).getTipus(tipus).body()
                val dades = response!!

                runOnUiThread {
                    Toast.makeText(
                        this@MainActivity,
                        "S'han recuperat " + dades.size + " valors",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
        }
        binding.btgetcodi.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val codi = 1
                val response = getRetrofit().create(APIService::class.java).getProducte(codi).body()
                val dades = response!!

                runOnUiThread {
                    Toast.makeText(
                        this@MainActivity,
                        "Producte "+dades[0].descripcio+" recuperat",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
        }
        binding.btafegeix.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                val producte = ProductesItem(null, "Prova 1", "Prova 2", "Prova 3", 20.3, 1, 1)
                val call =
                    getRetrofit().create(APIService::class.java).insertProducte(producte)
                if (call.isSuccessful) {
                    runOnUiThread {
                        val resposta = call.body()
                        Toast.makeText(this@MainActivity, resposta!!.missatge, Toast.LENGTH_LONG)
                            .show()
                    }
                }else{
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "No s'ha pogut afegir el producte", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
        binding.btmodifica.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val producte = ProductesItem(540, "Producte modificat", "Prova 2", "Prova 3", 20.3, 1, 1)
                val call =
                    getRetrofit().create(APIService::class.java).updateProducte(producte)
                if (call.isSuccessful) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, call.body()!!.missatge, Toast.LENGTH_LONG)
                            .show()
                    }
                }else {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "No s'ha pogut afegir el producte", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
        binding.btesborra.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val codi = 545
                val call = getRetrofit().create(APIService::class.java).deleteProducte(codi)
                if (call.isSuccessful){
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, call.body()!!.missatge, Toast.LENGTH_LONG)
                            .show()
                    }
                }else{
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "No s'ha pogut esborrar el producte", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder().baseUrl("http://172.16.38.50/").client(getClient())
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    private fun getClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(logging)
            .build()
}