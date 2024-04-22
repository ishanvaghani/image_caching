package com.example.imagecaching.domain.respository

import com.example.imagecaching.domain.model.Media

interface ImagesRepository {

    suspend fun fetchImages(limit: Int): List<Media>?
}