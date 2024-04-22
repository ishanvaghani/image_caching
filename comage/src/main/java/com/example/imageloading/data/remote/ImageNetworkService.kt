package com.example.imageloading.data.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ImageNetworkService {

    @GET
    suspend fun downloadImage(@Url url: String): Response<ResponseBody>
}