package com.example.elixirgamesapp.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.elixirgamesapp.data.network.api.VideoGameService
import com.example.elixirgamesapp.data.network.retrofit.RetrofitHelper
import com.example.elixirgamesapp.data.repository.VideoGameImpl
import com.example.elixirgamesapp.databinding.ActivityVideoGameDetailsBinding
import com.example.elixirgamesapp.domain.VideoGameUseCase
import com.example.elixirgamesapp.presentation.viewmodel.DetailViewModel
import com.example.elixirgamesapp.presentation.viewmodel.DetailViewModelFactory
import com.squareup.picasso.Picasso


class VideoGameDetails : AppCompatActivity() {

    private lateinit var binding : ActivityVideoGameDetailsBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoGameDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idVideoGame = intent.getLongExtra("ID_VIDEO_GAME", -1)
        if (idVideoGame == -1L) {
            finish()
            return
        }
        //binding.idGame.text = idVideoGame.toString()
        val apiService = RetrofitHelper.getRetrofit().create(VideoGameService::class.java)
        val repository = VideoGameImpl(apiService)
        val useCase = VideoGameUseCase(repository)
        val detailViewModelFactory = DetailViewModelFactory(useCase)
        viewModel = ViewModelProvider(this, detailViewModelFactory).get(DetailViewModel::class.java)

        viewModel.getDetailVideoGameById(idVideoGame)

        viewModel.videoGamedetails.observe(this){
            Log.i("DETAIL", it.toString())
                Picasso.get()
                    .load(it.backgroundImage)
                    .centerCrop()
                    .fit()
                    .into(binding.backgroundImage)
            binding.price.text = it.price.toString()
            binding.volverPersonajes.title = it.name
            binding.txtanho.text = it.released
            binding.txtgenero.text = it.genres
            binding.txtduracion.text = it.playtime.toString()
            binding.txtplataformas.text = it.platforms
            binding.ratingBar.setRating(it.rating.toFloat());


        }

    }
}