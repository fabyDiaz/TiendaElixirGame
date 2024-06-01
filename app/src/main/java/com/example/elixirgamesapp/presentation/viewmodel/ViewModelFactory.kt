package com.example.elixirgamesapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.elixirgamesapp.domain.VideoGameUseCase

class ViewModelFactory(private val useCase: VideoGameUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoGameViewModel::class.java)) {
            return VideoGameViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}