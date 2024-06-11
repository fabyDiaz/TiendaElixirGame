package com.example.elixirgamesapp.data.repository

import com.example.elixirgamesapp.data.response.VideoGameDetails
import com.example.elixirgamesapp.data.response.VideoGameResponse

interface VideoGameRepository {

    /**
     * Estos son los métodos para pder trabajar con el servicio d la API REST
     */
    suspend fun fetchVideoGames(): MutableList<VideoGameResponse>
    suspend fun fetchVideoGameById(idVideoGameService: Long): VideoGameDetails

    /**
     * Estos son los métodos para poder  trabajar con la base de datos y que la app se pueda utilizar sin conexión
     */
    suspend fun saveAllVideoGameOnDB(videoGames: MutableList<VideoGameResponse>)

    suspend fun getAllVideoGamesFromDB():MutableList<VideoGameResponse>

    suspend fun saveDatailsVideoGameOnDB(details: VideoGameDetails)

    suspend fun getDetailsVideoGamesFromDB(idVideoGame: Long): VideoGameDetails
}