package com.example.imagecaching.data.repository

import com.example.imagecaching.data.remote.ImagesService
import com.example.imagecaching.data.toMedia
import com.example.imagecaching.domain.model.Media
import com.example.imagecaching.domain.respository.ImagesRepository

class ImagesRepositoryImpl(private val imagesService: ImagesService) : ImagesRepository {

    override suspend fun fetchImages(limit: Int): List<Media>? {
        return try {
            val response = imagesService.fetchImages(limit)
            if (response.isSuccessful) {
                response.body()?.map { it.toMedia() }
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}