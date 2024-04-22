package com.example.imagecaching.domain.usecase

import com.example.imagecaching.domain.model.Media
import com.example.imagecaching.domain.respository.ImagesRepository

class ImagesUseCase(private val imagesRepository: ImagesRepository) {

    suspend fun fetchImages(limit: Int): List<Media>? {
        return imagesRepository.fetchImages(limit)
    }
}