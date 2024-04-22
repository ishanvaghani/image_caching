package com.example.imageloading.data.local

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.os.Environment.isExternalStorageRemovable
import com.example.imageloading.ComageModule
import com.example.imageloading.common.getRandomInt
import com.example.imageloading.data.local.common.getCacheKey
import com.example.imageloading.data.local.diskCache.DiskLruCache
import java.io.File
import java.io.FileOutputStream


class ImageDiskCacheService {

    companion object {
        private const val DISK_CACHE_SIZE = 1024 * 1024 * 200L // 200 MB
        private const val DISK_CACHE_DIR = "ComageCache"
    }

    private val cache = DiskLruCache.create(getCacheDir(), DISK_CACHE_SIZE)

    fun addImage(imageUrl: String, height: Int, width: Int, bitmap: Bitmap) {
        val cacheKey = getCacheKey(imageUrl, height, width)

        val fileDir = getCacheDir()
        if (!fileDir.exists()) {
            fileDir.mkdirs()
        }
        val file = File(fileDir, "${getRandomInt()}.png")
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.close()

        cache.put(cacheKey, file)
    }

    fun getImage(imageUrl: String, height: Int, width: Int): Bitmap? {
        val file = cache.get(getCacheKey(imageUrl, height, width)) ?: return null
        return BitmapFactory.decodeFile(file.absolutePath)
    }

    private fun getCacheDir(): File {
        val cachePath = if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
            || !isExternalStorageRemovable()
        ) {
            ComageModule.comageModuleBridge.getContext().externalCacheDir?.path
        } else {
            ComageModule.comageModuleBridge.getContext().cacheDir.path
        }
        return File(cachePath + File.separator + DISK_CACHE_DIR)
    }
}