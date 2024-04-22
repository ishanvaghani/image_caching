package com.example.imageloading.data.local

import android.graphics.Bitmap
import androidx.collection.LruCache
import com.example.imageloading.data.local.common.getCacheKey

class ImageMemoryCacheService {

    private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt() // KB
    private val cacheSize = maxMemory / 8

    private val memoryCache = object : LruCache<String, Bitmap>(cacheSize) {
        override fun sizeOf(key: String, bitmap: Bitmap): Int {
            return bitmap.byteCount / 1024
        }
    }

    fun getImage(imageUrl: String, height: Int, width: Int): Bitmap? {
        return memoryCache.get(getCacheKey(imageUrl, height, width))
    }

    fun addImage(imageUrl: String, height: Int, width: Int, bitmap: Bitmap) {
        memoryCache.put(getCacheKey(imageUrl, height, width), bitmap)
    }
}