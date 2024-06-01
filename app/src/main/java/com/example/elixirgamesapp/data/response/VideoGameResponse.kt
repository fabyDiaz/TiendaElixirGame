package com.example.elixirgamesapp.data.response

import com.google.gson.annotations.SerializedName

data class VideoGameResponse (
    val id: Long,
    val name: String,
    val released: String,
    @SerializedName("background_image")
    val backgroundImage: String,
    val rating: Double,
    val metacritic: Long){
}