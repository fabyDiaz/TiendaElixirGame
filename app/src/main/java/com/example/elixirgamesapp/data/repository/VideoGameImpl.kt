package com.example.elixirgamesapp.data.repository

import com.example.elixirgamesapp.data.local.dao.IVideoGameDao
import com.example.elixirgamesapp.data.network.api.VideoGameService
import com.example.elixirgamesapp.data.response.VideoGameDetails
import com.example.elixirgamesapp.data.response.VideoGameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoGameImpl(private var apiservice: VideoGameService,
                    private var daoDb: IVideoGameDao): VideoGameRepository
{

    /**
     * Implementación de la API REST de video Juegos
     */
    override suspend fun fetchVideoGames(): MutableList<VideoGameResponse> {
        return withContext(Dispatchers.IO){
            val listVideoGames=apiservice.getAllVideoGames()
            listVideoGames
        }
    }

    override suspend fun fetchVideoGameById(idVideoGameService: Long): VideoGameDetails {
        return withContext(Dispatchers.IO){
            val videoGameDetails = apiservice.getVideoGamesById(idVideoGameService)
            videoGameDetails
        }
    }

    /**
     * Estos son los métodos para implementar la base de datos
     */

    override suspend fun saveAllVideoGameOnDB(videoGames: MutableList<VideoGameResponse>) {
        return withContext(Dispatchers.IO){
            daoDb.insertVideoGame(videoGames)
        }

    }

    override suspend fun getAllVideoGamesFromDB(): MutableList<VideoGameResponse> {
        return withContext(Dispatchers.IO){
            daoDb.getAllVideoGames()
        }

    }

    override suspend fun saveDatailsVideoGameOnDB(details: VideoGameDetails) {
        return withContext(Dispatchers.IO){
            daoDb.insertVideoGameDetails(details)
        }

    }

    override suspend fun getDetailsVideoGamesFromDB(idVideoGame: Long): VideoGameDetails {
        return withContext(Dispatchers.IO){
            daoDb.getDetailsVideoGame(idVideoGame)
        }
    }


}