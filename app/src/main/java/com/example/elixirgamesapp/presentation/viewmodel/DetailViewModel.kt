package com.example.elixirgamesapp.presentation.viewmodel

import android.util.Log
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

    fun getDetailVideoGameById(idVideoGame : Long) {
        viewModelScope.launch {
            try{
                _videoGameDetails.value = useCase.getVideoGameoByIdOnStock(idVideoGame)
            }catch(e: Exception){
                Log.e("DetailActivity", "Not Network Connection")
                _videoGameDetails.value = useCase.getDetailsVideoGamesFromDB(idVideoGame)
            }

        }
    }
}