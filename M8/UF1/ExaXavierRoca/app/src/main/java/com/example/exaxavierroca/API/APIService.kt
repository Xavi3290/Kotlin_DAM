package com.example.exaxavierroca.API

import com.example.exaxavierroca.ResponseModel1
import com.example.exaxavierroca.ResponseModel2
import com.example.exaxavierroca.Usuari
import com.example.exaxavierroca.Usuari2
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @GET("/usuaris/")
    suspend fun existeixUsuari(@Query("usuari") nomusuari:String): Response<ResponseModel1>

 /*   @GET("/usuaris/?usuari={nomusuari}")
    suspend fun existeixUsuari(@Path("nomusuari") nomusuari:String): Response<ResponseModel1>
*/
    @POST("/usuaris/")
    suspend fun afegeixUsuari(@Body usuari: Usuari): Response<ResponseModel1>

    @POST("/usuaris/")
    suspend fun loguejaUsuari(@Body usuari: Usuari2): Response<ResponseModel2>

}