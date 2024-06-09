package com.example.elixirgamesapp.data.repository

import com.example.elixirgamesapp.data.response.VideoGameDetails
import com.example.elixirgamesapp.data.response.VideoGameResponse

interface VideoGameRepository {

    suspend fun fetchVideoGames(): MutableList<VideoGameResponse>
    suspend fun fetchVideoGameById(idVideoGameService: Long): VideoGameDetails
}