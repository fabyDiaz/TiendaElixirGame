package com.example.elixirgamesapp.presentation.viewmodel

import android.util.Log
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
        // Cargar datos desde la base de datos al iniciar
        loadVideoGamesFromDB()
    }

    private fun loadVideoGamesFromDB() {
        viewModelScope.launch {
            try {
                val dbResponse = useCase.getAllVideoGameDB()
                _allVideoGame.postValue(dbResponse)
                Log.i("VideoGameViewModel", "Loaded games from DB: $dbResponse")
            } catch (e: Exception) {
                Log.e("VideoGameViewModel", "Error loading games from DB: ${e.message}")
            }
        }
    }

    fun getAllVideoGamesFromServer() {
        viewModelScope.launch {
            try {
                val response = useCase.getAllVideoGameoOnStock()
                if (response.isNotEmpty()) {
                    // Guardar en la base de datos
                    useCase.saveAllVideoGameDB(response)
                    response.forEach { videoGame ->
                        val detailResponse = useCase.getVideoGameoByIdOnStock(videoGame.id)
                        if (detailResponse != null) {
                            useCase.saveDatailsVideoGameOnDB(detailResponse)
                        } else {
                            Log.e("VideoGameViewModel", "Detail response for video game ID ${videoGame.id} is null")
                        }
                    }
                }
                // Actualizar LiveData siempre, incluso si la respuesta está vacía
                _allVideoGame.postValue(response)
                Log.i("VideoGameViewModel", "All video games: $response")
            } catch (e: Exception) {
                Log.e("VideoGameViewModel", "Error: ${e.message}")
                e.printStackTrace()  // Esto imprimirá el stack trace completo en el logcat
            }
        }
    }

}