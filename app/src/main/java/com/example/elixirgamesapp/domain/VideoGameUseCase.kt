package com.example.elixirgamesapp.domain

import com.example.elixirgamesapp.data.repository.VideoGameImpl
import com.example.elixirgamesapp.data.response.VideoGameDetails
import com.example.elixirgamesapp.data.response.VideoGameResponse

class VideoGameUseCase(private val repository: VideoGameImpl) {

    suspend fun getAllVideoGameoOnStock(): MutableList<VideoGameResponse>{
        return repository.fetchVideoGames()
    }

    suspend fun getVideoGameoByIdOnStock(idVideoGame: Long): VideoGameDetails{
        return repository.fetchVideoGameById(idVideoGame)
    }

}
