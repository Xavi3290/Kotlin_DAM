package com.example.practicaexamenuf1xavierroca.API

import com.example.practicaexamenuf1xavierroca.Producte
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class CrudApi(): CoroutineScope {

    val urlapi = "http://172.16.24.50/"
//    val urlapi = "http://192.168.1.135/"
//    val urlapi = "http://172.19.254.119/"

    private var job: Job = Job()

    private fun getRetrofit(): Retrofit {     // millor fer servir aquesta funció
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder().baseUrl(urlapi).client(getClient())
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    private fun getClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())     // estos addInterceptor no hacen falta
            //.addInterceptor(logging)
            .build()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    suspend fun getAllFromAPI(): ArrayList<Producte>{
        val response = getRetrofit().create(APIService::class.java).getProductes().body()
        return response!!
    }

    suspend fun getProducte(codi: Int): Producte {
        val response = getRetrofit().create(APIService::class.java).getProducte(codi).body()
        return response!!
    }

    suspend fun addProducteToAPI(producte: Producte): Boolean {
        val call = getRetrofit().create(APIService::class.java).insertProducte(producte)
        return call.isSuccessful
    }


}