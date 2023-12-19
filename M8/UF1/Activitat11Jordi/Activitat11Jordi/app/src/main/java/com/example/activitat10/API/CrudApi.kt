package com.example.activitat10.API

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.activitat10.Producte
import com.example.activitat10.Productes
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class CrudApi(): CoroutineScope {

    val urlapi = "http://172.16.24.50/"
//    val urlapi = "http://192.168.1.135/"
//    val urlapi = "http://172.19.254.119/"

    private var job: Job = Job()

    suspend fun getTipusFromApi(tipus: Int): Productes {
           val response = getRetrofit().create(APIService::class.java).getTipus(tipus).body()
           return response!!
    }

    suspend fun getAllFromAPI(): Productes {
        val response = getRetrofit().create(APIService::class.java).getProductes().body()
        return response!!
    }

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
            .addInterceptor(logging)
            .build()

    suspend fun getCodiFromApi(codi: Int): ArrayList<Producte> {
                val response = getRetrofit().create(APIService::class.java).getProducte(codi).body()
                return response!!
    }

    suspend fun addProducteToAPI(producte: Producte): Boolean {
        val call = getRetrofit().create(APIService::class.java).insertProducte(producte)
        return call.isSuccessful
    }

    suspend fun modifyProducteFromApi(producte: Producte): Boolean {
        val call = getRetrofit().create(APIService::class.java).updateProducte(producte)
        return call.isSuccessful
    }

    suspend fun esborraProducteAPI(codi: Int): Boolean {
        val call = getRetrofit().create(APIService::class.java).deleteProducte(codi)
        return call.isSuccessful
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


}

