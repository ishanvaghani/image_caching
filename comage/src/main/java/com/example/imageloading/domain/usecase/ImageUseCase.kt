package com.example.imageloading.domain.usecase

import android.graphics.Bitmap
import com.example.imageloading.domain.repository.ImageRepository

class ImageUseCase(private val imageRepository: ImageRepository) {

    suspend fun downloadImage(imageUrl: String, height: Int, width: Int): Bitmap? {
        return imageRepository.downloadImage(imageUrl, height, width)
    }
}