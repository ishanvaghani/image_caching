package com.example.imageloading.common

import android.content.Context
import kotlin.random.Random
import kotlin.random.nextInt

const val TAG = "COMAGE"

fun getScreenWidth(context: Context?): Int {
    if (context == null) {
        return 0
    }
    val metrics = context.resources.displayMetrics
    return metrics.widthPixels
}

fun getScreenHeight(context: Context?): Int {
    if (context == null) {
        return 0
    }
    val metrics = context.resources.displayMetrics
    return metrics.heightPixels
}

fun getRandomInt(): Int {
    return Random.nextInt(IntRange(1000, 100000))
}