package com.example.imagecaching.data.remote

import com.example.imagecaching.data.remote.dto.MediaDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesService {

    @GET("v2/content/misc/media-coverages")
    suspend fun fetchImages(@Query("limit") limit: Int): Response<List<MediaDto>>
}