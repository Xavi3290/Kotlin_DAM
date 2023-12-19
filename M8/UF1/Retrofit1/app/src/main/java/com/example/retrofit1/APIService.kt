package com.example.retrofit1

import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @GET("/api/v1/employees")
    suspend fun getEmployees():Response<Dada>

    @GET("/api/v1/employee/{Id}")
    suspend fun getEmployee(@Path("Id") employeeId: String): Response<Dada>

    @POST("/api/v1/create")
    suspend fun insertEmployee(@Body dada: Dada): Response<PostResponse>

    @PUT("/api/v1/update/{Id}")
    suspend fun updateEmployee(@Path("Id") employeeId: String, @Body dada: Dada): Response<PostResponse>

    @DELETE("/api/v1/delete/{id}")
    suspend fun deleteEmployee(@Path("Id") employeeId: String): Response<Dada>
}