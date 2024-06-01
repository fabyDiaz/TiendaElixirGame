package com.example.elixirgamesapp.data.network.api

import com.example.elixirgamesapp.data.network.retrofit.RetrofitHelper
import com.example.elixirgamesapp.data.response.VideoGameResponse

class VideoGamesApiClient {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getVideoGames(): MutableList<VideoGameResponse>{
        val response = retrofit.create(VideoGameService::class.java).getAllVideoGames()
        return response
    }
}