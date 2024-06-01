package com.example.elixirgamesapp.data.repository

import com.example.elixirgamesapp.data.network.api.VideoGameService
import com.example.elixirgamesapp.data.response.VideoGameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoGameImpl(private var apiservice: VideoGameService): VideoGameRepository{
    override suspend fun fetchVideoGames(): MutableList<VideoGameResponse> {
        return withContext(Dispatchers.IO){
            val listVideoGames=apiservice.getAllVideoGames()
            listVideoGames
        }
    }


}