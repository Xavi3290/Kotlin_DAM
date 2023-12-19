package com.example.exempleimageuploadfromgallery.API

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.Response
import retrofit2.http.*

// https://www.youtube.com/watch?v=aY9xsGMlC5c&ab_channel=GenericApps
// https://davidamunga.medium.com/working-with-multipart-form-data-using-retrofit-for-android-280307f23258
// https://www.youtube.com/watch?v=L3pM5YuxYp4&ab_channel=Programaci%C3%B3nAndroidbyAristiDevs 19:45

interface ApiService {
    @Multipart
    @POST("/imatges/")
    suspend fun pujarArxiu(
        @Part image: MultipartBody.Part
    ): Response<UploadResponse>
}