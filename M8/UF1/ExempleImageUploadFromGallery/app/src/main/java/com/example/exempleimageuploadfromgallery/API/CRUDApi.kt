package com.example.exempleimageuploadfromgallery.API

import android.text.TextUtils.indexOf
import android.text.TextUtils.substring
import android.util.Log
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import kotlin.coroutines.CoroutineContext


class CrudApi(): CoroutineScope {



    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private fun getRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder().baseUrl(urlapi).client(getClient())
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    private fun getClient(): OkHttpClient {
        var logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
    }

    fun pujaArxiu(rutaArxiu: String): UploadResponse? {
        Log.i("nomArxiu", rutaArxiu)
        var novarutaArxiu = substring(rutaArxiu, indexOf(rutaArxiu,"/storage"), rutaArxiu.length)
        val file = File(novarutaArxiu)
        Log.i("nomArxiu", novarutaArxiu)
        if (!file.exists()) {
            Log.i("Error", "La ruta " + novarutaArxiu + " no existeix")
            return null
        } else {
            val photo: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("image", file.getName(), photo)

            var response: Response<UploadResponse>? = null
            runBlocking {
                val corrutina = launch {
                    response = getRetrofit().create(ApiService::class.java).pujarArxiu(body)!!
                }
                corrutina.join()
            }
            if (response!!.isSuccessful)
                return response!!.body()!!
            else {
                Log.i("Error", response!!.code().toString())
                return null
            }
        }
    }
}