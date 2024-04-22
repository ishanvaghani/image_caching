package com.example.imagecaching.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagecaching.domain.model.Media
import com.example.imagecaching.domain.usecase.ImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface UIEvent {
    data object Loading : UIEvent
    data class Error(val error: String) : UIEvent
    data class Success(val data: List<Media>) : UIEvent
}

@HiltViewModel
class ImagesViewModel @Inject constructor(private val imagesUseCase: ImagesUseCase) : ViewModel() {

    private val _imagesFlow = MutableStateFlow<UIEvent>(UIEvent.Loading)
    val imagesFlow = _imagesFlow.asStateFlow()

    fun fetchImages(limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = imagesUseCase.fetchImages(limit)
            if (response.isNullOrEmpty()) {
                _imagesFlow.emit(UIEvent.Error("Something went wrong"))
            } else {
                _imagesFlow.emit(UIEvent.Success(response))
            }
        }
    }
}