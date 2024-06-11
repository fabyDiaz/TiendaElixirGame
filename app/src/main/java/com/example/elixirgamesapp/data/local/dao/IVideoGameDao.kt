package com.example.elixirgamesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.elixirgamesapp.data.response.VideoGameDetails
import com.example.elixirgamesapp.data.response.VideoGameResponse

@Dao
interface IVideoGameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideoGame(VideoGameResponse: MutableList<VideoGameResponse>)
    @Query("SELECT * FROM videogames")
    suspend fun getAllVideoGames():  MutableList<VideoGameResponse>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideoGameDetails(videoGameDetail: VideoGameDetails)
    @Query("SELECT * FROM videogames_details WHERE id= :idVideoGame")
    suspend fun getDetailsVideoGame(idVideoGame: Long): VideoGameDetails

}