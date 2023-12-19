package com.example.retrofit2


import Productes
import ProductesItem
import ResponseModel
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @GET("/productes/?llistat")
    suspend fun getProductes():Response<Productes>

    @GET("/productes/")
    suspend fun getProducte(@Query("codi") codi: Int): Response<Productes>

    @GET("/productes/")
    suspend fun getTipus(@Query("tipus") tipus: String): Response<Productes>

    @POST("/productes/")
    suspend fun insertProducte(@Body producte: ProductesItem): Response<ResponseModel>

    @PUT("/productes/")
    suspend fun updateProducte(@Body dada: ProductesItem): Response<ResponseModel>

    @DELETE("/productes/")
    suspend fun deleteProducte(@Query("codi") codi: Int): Response<ResponseModel>
}