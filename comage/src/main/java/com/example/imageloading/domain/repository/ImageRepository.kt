package com.example.imageloading.domain.repository

import android.graphics.Bitmap

interface ImageRepository {

    suspend fun downloadImage(imageUrl: String, height: Int, width: Int): Bitmap?
}