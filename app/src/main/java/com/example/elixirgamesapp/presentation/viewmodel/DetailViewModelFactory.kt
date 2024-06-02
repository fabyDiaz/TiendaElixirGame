package com.example.elixirgamesapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.elixirgamesapp.domain.VideoGameUseCase

class DetailViewModelFactory (private val useCase: VideoGameUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}