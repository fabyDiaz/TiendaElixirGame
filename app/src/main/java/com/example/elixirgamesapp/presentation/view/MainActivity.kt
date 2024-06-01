package com.example.elixirgamesapp.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elixirgamesapp.R
import com.example.elixirgamesapp.data.network.api.VideoGameService
import com.example.elixirgamesapp.data.network.retrofit.RetrofitHelper
import com.example.elixirgamesapp.data.repository.VideoGameImpl
import com.example.elixirgamesapp.data.repository.VideoGameRepository
import com.example.elixirgamesapp.databinding.ActivityMainBinding
import com.example.elixirgamesapp.domain.VideoGameUseCase
import com.example.elixirgamesapp.presentation.adapter.VideoGameAdapter
import com.example.elixirgamesapp.presentation.viewmodel.VideoGameViewModel
import com.example.elixirgamesapp.presentation.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var videoGameAdapter: VideoGameAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = RetrofitHelper.getRetrofit().create(VideoGameService::class.java)
        val repository = VideoGameImpl(apiService)
        val useCase = VideoGameUseCase(repository)
        val viewModelFactory = ViewModelFactory(useCase)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(VideoGameViewModel::class.java)

        videoGameAdapter = VideoGameAdapter()
        binding.recyclerVideogame.layoutManager = LinearLayoutManager(this)
        binding.recyclerVideogame.adapter = videoGameAdapter

        viewModel.allVideoGame.observe(this) { videos ->
            Log.i("GAME", videos.toString())
            videoGameAdapter.videogames = videos.toMutableList()
            videoGameAdapter.notifyDataSetChanged()
        }
      /* val adapterVideoGame = VideoGameAdapter()
        binding.recyclerVideogame.adapter = adapterVideoGame
        binding.recyclerVideogame.layoutManager = LinearLayoutManager(this)

        viewModel.allVideoGame.observe(this){
            Log.i("GAME", it.toString())
        }*/



    }
}