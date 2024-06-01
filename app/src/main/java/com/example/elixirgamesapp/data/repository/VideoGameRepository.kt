package com.example.elixirgamesapp.data.repository

import com.example.elixirgamesapp.data.response.VideoGameResponse

interface VideoGameRepository {

    suspend fun fetchVideoGames(): MutableList<VideoGameResponse>
}