package com.example.elixirgamesapp.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "videogames_details")
data class VideoGameDetails (
    @PrimaryKey
    val id: Long,
    val name: String,
    val released: String,
    @SerializedName("background_image")
    val backgroundImage: String,
    val rating: Double,
    val metacritic: Long,
    val playtime: Long,
    val platforms: String,
    val genres: String,
    val format: String,
    val price: Long,
    val lastPrice: Long,
    val delivery: Boolean){

}
