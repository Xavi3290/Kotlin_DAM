package com.example.activitat9bona

import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @GET("/producte")
    suspend fun getProductesApi(): Response<Producte_Json>

    @GET("/producte/{codi}")
    suspend fun getProducteApi(@Path("codi") producteCodi: Int): Response<Producte_JsonItem>

    @POST("/producte")
    suspend fun insertProducte(@Body producte: Producte_JsonItem): Response<PostResponse>

    @PUT("/producte/{codi}")
    suspend fun updateProducte(@Path("codi") producteCodi: Int, @Body producte: Producte_JsonItem): Response<PostResponse>

    @DELETE("/producte/{codi}")
    suspend fun deleteProducte(@Path("codi") producteCodi: Int): Response<PostResponse>

}