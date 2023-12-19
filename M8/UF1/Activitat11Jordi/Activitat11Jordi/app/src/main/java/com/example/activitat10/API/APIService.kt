package com.example.activitat10.API

import com.example.activitat10.Producte
import com.example.activitat10.Productes
import com.example.activitat10.ResponseModel
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @GET("/productes/?llistat")
    suspend fun getProductes():Response<Productes>

    @GET("/productes/")
    suspend fun getProducte(@Query("codi") codi: Int): Response<Productes>

    @GET("/productes/")
    suspend fun getTipus(@Query("tipus") tipus: Int): Response<Productes>

    @POST("/productes/")
    suspend fun insertProducte(@Body producte: Producte): Response<ResponseModel>

    @PUT("/productes/")
    suspend fun updateProducte(@Body producte: Producte): Response<ResponseModel>

    @DELETE("/productes/")
    suspend fun deleteProducte(@Query("codi") codi: Int): Response<ResponseModel>
}