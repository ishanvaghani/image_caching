package com.example.imageloading.data.local.common

import android.graphics.Bitmap
import com.example.imageloading.ComageModule
import com.example.imageloading.common.getScreenHeight
import com.example.imageloading.common.getScreenWidth

fun getCacheKey(imageUrl: String, height: Int, width: Int): String {
    return imageUrl + "_" + height + "_" + width
}

fun scaleBitmap(bitmap: Bitmap?, width: Int, height: Int): Bitmap? {
    if (bitmap == null) {
        return null
    }

    var mWidth = width
    var mHeight = height
    if (mWidth == 0 || mHeight == 0) {
        mWidth = getScreenWidth(ComageModule.comageModuleBridge.getContext())
        mHeight = getScreenHeight(ComageModule.comageModuleBridge.getContext())
    }

    return Bitmap.createScaledBitmap(bitmap, mWidth, mHeight, false)
}