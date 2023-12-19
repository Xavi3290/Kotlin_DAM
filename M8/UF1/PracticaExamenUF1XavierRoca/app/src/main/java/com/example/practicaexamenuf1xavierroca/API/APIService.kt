package com.example.practicaexamenuf1xavierroca.API

import com.example.practicaexamenuf1xavierroca.Producte
import com.example.practicaexamenuf1xavierroca.ResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {

    @GET("/productes/?llistat")
    suspend fun getProductes(): Response<ArrayList<Producte>>

    @GET("/productes/")
    suspend fun getProducte(@Query("codi") codi: Int): Response<Producte>

    @POST("/productes/")
    suspend fun insertProducte(@Body producte: Producte): Response<ResponseModel>
}