package com.example.exaxavierroca.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("/v2/directions/foot-walking/")
    suspend fun getRutaCaminant(
        @Query("api_key") apikey: String,
        @Query("start") start: String,
        @Query("end") end: String
    ): Response<Resposta>

    @GET("/v2/directions/driving-car/")
    suspend fun getRutaCotxe(
        @Query("api_key") apikey: String,
        @Query("start") start: String,
        @Query("end") end: String
    ): Response<Resposta>
}