package com.example.imagecaching

import android.app.Application
import com.example.imagecaching.mouleHelper.ComageModuleHelper
import com.example.imageloading.ComageModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ImageCachingApp : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
        ComageModule.init(ComageModuleHelper())
    }

    companion object {
        lateinit var instance: ImageCachingApp
    }
}