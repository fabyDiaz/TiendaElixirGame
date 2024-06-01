package com.example.elixirgamesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elixirgamesapp.data.response.VideoGameResponse
import com.example.elixirgamesapp.domain.VideoGameUseCase
import kotlinx.coroutines.launch

class VideoGameViewModel(private val useCase: VideoGameUseCase): ViewModel(){

    /*  private var videoGameList= MutableLiveData<MutableList<VideoGameResponse>>()

    val videoGameLv
        get() = videoGameList

    init{
        viewModelScope.launch {
            videoGameList.value = useCase.getAllVideoGameoOnStock()
        }
    }*/
    private val _allVideoGame = MutableLiveData<MutableList<VideoGameResponse>>()

    val allVideoGame: LiveData<MutableList<VideoGameResponse>>
        get() = _allVideoGame

    init {
        getAllVideoGames()
    }

    fun getAllVideoGames() {
        viewModelScope.launch {
            _allVideoGame.value = useCase.getAllVideoGameoOnStock()
        }
    }

}