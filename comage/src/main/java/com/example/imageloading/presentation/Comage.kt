package com.example.imageloading.presentation

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.example.imageloading.ComageModule
import com.example.imageloading.common.TAG
import com.example.imageloading.di.ImageUseCaseEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException

object Comage : CoroutineScope {

    private val imageUseCaseEntryPoint by lazy {
        EntryPointAccessors.fromApplication(
            ComageModule.comageModuleBridge.getContext(),
            ImageUseCaseEntryPoint::class.java
        )
    }

    fun loadImage(imageView: ImageView, imageUrl: String, placeHolder: Drawable? = null) {
        imageView.setImageDrawable(placeHolder)
        loadImage(imageView, imageUrl)
    }

    private fun loadImage(imageView: ImageView, imageUrl: String) {
        val job = launch {
            val bitmap = imageUseCaseEntryPoint.imageUseCase.downloadImage(
                imageUrl,
                imageView.height,
                imageView.height
            )
            if (bitmap != null) {
                withContext(Dispatchers.Main) {
                    imageView.setImageBitmap(bitmap)
                }
            }
        }

        imageView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
            }

            override fun onViewDetachedFromWindow(v: View) {
                job.cancel(CancellationException("Image is not attached to a window"))
            }
        })
    }

    override val coroutineContext: CoroutineContext =
        Dispatchers.IO + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            Log.d(TAG, "Exception: $throwable")
        }
}