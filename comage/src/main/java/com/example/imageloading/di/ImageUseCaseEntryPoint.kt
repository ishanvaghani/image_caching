package com.example.imageloading.di

import com.example.imageloading.domain.usecase.ImageUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ImageUseCaseEntryPoint {
    val imageUseCase: ImageUseCase
}