package com.example.elixirgamesapp.data.network.api

import com.example.elixirgamesapp.data.response.VideoGameResponse
import retrofit2.http.GET

interface VideoGameService {

    @GET("games")
    suspend fun getAllVideoGames(): MutableList<VideoGameResponse>

}