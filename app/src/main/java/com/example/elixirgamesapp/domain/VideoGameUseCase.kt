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

    suspend fun saveAllVideoGameDB(videoGame: MutableList<VideoGameResponse>){
        return repository.saveAllVideoGameOnDB(videoGame)
    }

    suspend fun getAllVideoGameDB():MutableList<VideoGameResponse>{
        return repository.getAllVideoGamesFromDB()
    }

    suspend fun saveDatailsVideoGameOnDB(details: VideoGameDetails){
        return repository.saveDatailsVideoGameOnDB(details)
    }

    suspend fun getDetailsVideoGamesFromDB(idVideoGame: Long): VideoGameDetails{
        return repository.getDetailsVideoGamesFromDB(idVideoGame)
    }

}
