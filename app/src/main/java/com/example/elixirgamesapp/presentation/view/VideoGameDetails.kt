package com.example.elixirgamesapp.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.elixirgamesapp.data.network.api.VideoGameService
import com.example.elixirgamesapp.data.network.retrofit.RetrofitHelper
import com.example.elixirgamesapp.data.repository.VideoGameImpl
import com.example.elixirgamesapp.databinding.ActivityVideoGameDetailsBinding
import com.example.elixirgamesapp.domain.VideoGameUseCase
import com.example.elixirgamesapp.presentation.viewmodel.VideoGameViewModel
import com.example.elixirgamesapp.presentation.viewmodel.ViewModelFactory


class VideoGameDetails : AppCompatActivity() {

    private lateinit var binding : ActivityVideoGameDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityVideoGameDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idVideoGame = intent.getLongExtra("ID_VIDEO_GAME", -1)
        binding.idGame.text = idVideoGame.toString()
        if(idVideoGame == -1L){
            finish()
        }

        val apiService = RetrofitHelper.getRetrofit().create(VideoGameService::class.java)
        val repository = VideoGameImpl(apiService)
        val useCase = VideoGameUseCase(repository)
        val viewModelFactory = ViewModelFactory(useCase)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)







    }
}