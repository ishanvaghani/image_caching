package com.example.imagecaching.mouleHelper

import android.content.Context
import com.example.imagecaching.ImageCachingApp
import com.example.imageloading.ComageModuleBridge

class ComageModuleHelper: ComageModuleBridge {

    override fun getContext(): Context {
        return ImageCachingApp.instance
    }
}