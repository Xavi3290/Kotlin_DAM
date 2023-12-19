package com.example.exaxavierroca.API

import com.example.exaxavierroca.ResponseModel1
import com.example.exaxavierroca.ResponseModel2
import com.example.exaxavierroca.Usuari
import com.example.exaxavierroca.Usuari2
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

    suspend fun getUsuari(nomusuari:String): ResponseModel1{
        val response = getRetrofit().create(APIService::class.java).existeixUsuari(nomusuari).body()
        return response!!
    }

    suspend fun addUsuari(usuari: Usuari): ResponseModel1{
        val response = getRetrofit().create(APIService::class.java).afegeixUsuari(usuari).body()
        return response!!
    }

    suspend fun addLogUsuari(usuari: Usuari2): ResponseModel2{
        val response = getRetrofit().create(APIService::class.java).loguejaUsuari(usuari).body()
        return response!!
    }

}