package com.example.imageloading.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.imageloading.common.TAG
import com.example.imageloading.data.local.ImageDiskCacheService
import com.example.imageloading.data.local.ImageMemoryCacheService
import com.example.imageloading.data.local.common.scaleBitmap
import com.example.imageloading.data.remote.ImageNetworkService
import com.example.imageloading.domain.repository.ImageRepository

class ImageRepositoryImpl(
    private val imageNetworkService: ImageNetworkService,
    private val imageMemoryCacheService: ImageMemoryCacheService,
    private val imageDiskCacheService: ImageDiskCacheService
) : ImageRepository {

    override suspend fun downloadImage(imageUrl: String, height: Int, width: Int): Bitmap? {
        return try {
            val memoryBitmap = imageMemoryCacheService.getImage(imageUrl, height, width)
            if (memoryBitmap != null) {
                memoryBitmap
            } else {
                val diskBitmap = imageDiskCacheService.getImage(imageUrl, height, width)
                if (diskBitmap != null) {
                    imageMemoryCacheService.addImage(imageUrl, height, width, diskBitmap)
                    diskBitmap
                } else {
                    val byteStream = imageNetworkService.downloadImage(imageUrl).body()?.byteStream()
                    val remoteBitmap = scaleBitmap(BitmapFactory.decodeStream(byteStream), height, width)
                    if (remoteBitmap != null) {
                        imageMemoryCacheService.addImage(imageUrl, height, width, remoteBitmap)
                        imageDiskCacheService.addImage(imageUrl, height, width, remoteBitmap)
                    }
                    byteStream?.close()
                    remoteBitmap
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "downloadImage: " + e.message)
            null
        }
    }
}