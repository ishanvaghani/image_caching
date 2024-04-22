package com.example.imageloading.di

import com.example.imageloading.data.local.ImageDiskCacheService
import com.example.imageloading.data.local.ImageMemoryCacheService
import com.example.imageloading.data.remote.ImageNetworkService
import com.example.imageloading.data.repository.ImageRepositoryImpl
import com.example.imageloading.domain.repository.ImageRepository
import com.example.imageloading.domain.usecase.ImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LibModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideImageNetworkService(okHttpClient: OkHttpClient): ImageNetworkService {
        return Retrofit.Builder()
            .baseUrl("https://google.com")
            .client(okHttpClient)
            .build()
            .create(ImageNetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideImageMemoryCacheService(): ImageMemoryCacheService {
        return ImageMemoryCacheService()
    }

    @Provides
    @Singleton
    fun provideImageDiskCacheService(): ImageDiskCacheService {
        return ImageDiskCacheService()
    }

    @Provides
    @Singleton
    fun provideImageRepository(
        imageNetworkService: ImageNetworkService,
        imageMemoryCacheService: ImageMemoryCacheService,
        imageDiskCacheService: ImageDiskCacheService
    ): ImageRepository {
        return ImageRepositoryImpl(
            imageNetworkService,
            imageMemoryCacheService,
            imageDiskCacheService
        )
    }

    @Provides
    @Singleton
    fun provideImageUseCase(imageRepository: ImageRepository): ImageUseCase {
        return ImageUseCase(imageRepository)
    }
}
