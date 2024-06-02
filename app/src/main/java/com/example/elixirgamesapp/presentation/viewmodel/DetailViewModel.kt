package com.example.elixirgamesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elixirgamesapp.data.response.VideoGameDetails
import com.example.elixirgamesapp.domain.VideoGameUseCase
import kotlinx.coroutines.launch

class DetailViewModel (private val useCase: VideoGameUseCase) : ViewModel(){

    private val _videoGameDetails = MutableLiveData<VideoGameDetails>()

    val videoGamedetails: MutableLiveData<VideoGameDetails>
        get() = _videoGameDetails

    init {
        getDetailVideoGameById(idVideoGame = -1)
    }

    fun getDetailVideoGameById(idVideoGame : Long) {
        viewModelScope.launch {
            _videoGameDetails.value = useCase.getVideoGameoByIdOnStock(idVideoGame)
        }
    }
}