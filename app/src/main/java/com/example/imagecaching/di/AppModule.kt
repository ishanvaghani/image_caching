package com.example.imagecaching.di

import com.example.imagecaching.data.remote.ImagesService
import com.example.imagecaching.data.repository.ImagesRepositoryImpl
import com.example.imagecaching.domain.respository.ImagesRepository
import com.example.imagecaching.domain.usecase.ImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideImagesService(): ImagesService {
        return Retrofit.Builder()
            .baseUrl("https://acharyaprashant.org/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImagesService::class.java)
    }

    @Provides
    @Singleton
    fun provideImagesRepository(): ImagesRepository {
        return ImagesRepositoryImpl(provideImagesService())
    }

    @Provides
    @Singleton
    fun provideImagesUseCase(): ImagesUseCase {
        return ImagesUseCase(provideImagesRepository())
    }
}