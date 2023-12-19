package com.example.provaretrofit.API

import com.example.provaretrofit.Producte
import com.example.provaretrofit.ResponseModel
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @GET("/productes/?llistat")
    suspend fun getProductes(): Response<ArrayList<Producte>>

    @GET("/productes/")
    suspend fun getProducte(@Query("codi") codi: Int): Response<Producte>

    @PUT("/productes/")
    suspend fun updateProducte(@Body producte: Producte): Response<ResponseModel>   //cambiarlo a insert POST

    @POST("/productes/")
    suspend fun insertProducte(@Body producte: Producte): Response<ResponseModel>
}