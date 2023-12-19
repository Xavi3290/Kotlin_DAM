package com.example.webservice2

import com.example.webservice2.dataclasses.BreedsCatsResponse
import com.example.webservice2.dataclasses.ImageCatsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getBreedsCats(@Url url: String): Response<List<BreedsCatsResponse>>
    @GET
    suspend fun getImageBreedsCats(@Url url: String): Response<List<ImageCatsResponse>>
}