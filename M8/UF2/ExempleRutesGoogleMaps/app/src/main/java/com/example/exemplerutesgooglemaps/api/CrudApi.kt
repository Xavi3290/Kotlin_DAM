package com.example.exemplerutesgooglemaps.api

import android.content.res.Resources
import com.example.exemplerutesgooglemaps.R
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class CrudAPI() : CoroutineScope {

    val urlapi = "https://api.openrouteservice.org/"
    val apikey = "5b3ce3597851110001cf6248235a4d737f7c4aaaaff21b820951f57b"

    private var job: Job = Job()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(urlapi)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    suspend fun getRutaCaminant(inici: String, final: String): List<List<Double>>? {
        var response: Response<Resposta>? = null

        val corrutina = launch {
            response =
                getRetrofit().create(APIService::class.java)
                    .getRutaCaminant(apikey, inici, final)
        }
        corrutina.join()

        if (response!!.isSuccessful) {
            return response!!.body()!!.features[0].geometry.coordinates
        } else{
            return null
        }
    }

    suspend fun getRutaCotxe(inici: String, final: String): List<List<Double>>? {
        var response: Response<Resposta>? = null

        val corrutina = launch {
            response =
                getRetrofit().create(APIService::class.java)
                    .getRutaCotxe(apikey, inici, final)
        }
        corrutina.join()

        if (response!!.isSuccessful) {
            return response!!.body()!!.features[0].geometry.coordinates
        } else{
            return null
        }
    }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

}