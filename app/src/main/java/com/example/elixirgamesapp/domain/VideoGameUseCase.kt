package com.example.elixirgamesapp.domain

import com.example.elixirgamesapp.data.repository.VideoGameImpl
import com.example.elixirgamesapp.data.response.VideoGameResponse

class VideoGameUseCase(private val repository: VideoGameImpl) {

    suspend fun getAllVideoGameoOnStock(): MutableList<VideoGameResponse>{
        return repository.fetchVideoGames()
    }
}
