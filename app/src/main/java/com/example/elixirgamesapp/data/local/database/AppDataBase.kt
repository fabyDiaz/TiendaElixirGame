package com.example.elixirgamesapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.elixirgamesapp.data.local.dao.IVideoGameDao
import com.example.elixirgamesapp.data.response.VideoGameDetails
import com.example.elixirgamesapp.data.response.VideoGameResponse

@Database(entities = [VideoGameResponse::class, VideoGameDetails::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase(){

    abstract fun videoGameDao(): IVideoGameDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "videogame_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}