package com.example.elixirgamesapp.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elixirgamesapp.R
import com.example.elixirgamesapp.data.local.database.AppDataBase
import com.example.elixirgamesapp.data.network.api.VideoGameService
import com.example.elixirgamesapp.data.network.retrofit.RetrofitHelper
import com.example.elixirgamesapp.data.repository.VideoGameImpl
import com.example.elixirgamesapp.databinding.FragmentVideoGameBinding
import com.example.elixirgamesapp.domain.VideoGameUseCase
import com.example.elixirgamesapp.presentation.adapter.VideoGameAdapter
import com.example.elixirgamesapp.presentation.viewmodel.VideoGameViewModel
import com.example.elixirgamesapp.presentation.viewmodel.ViewModelFactory

class VideoGameFragment : Fragment() {

    private var _binding: FragmentVideoGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var videoGameAdapter: VideoGameAdapter

    private lateinit var viewModel: VideoGameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVideoGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa viewModel y otros objetos que requieren contexto aquí
        val apiService = RetrofitHelper.getRetrofit().create(VideoGameService::class.java)
        val dataBase = AppDataBase.getDatabase(requireContext().applicationContext)
        val repository = VideoGameImpl(apiService, dataBase.videoGameDao())
        val useCase = VideoGameUseCase(repository)
        val viewModelFactory = ViewModelFactory(useCase)
        viewModel = ViewModelProvider(this, viewModelFactory).get(VideoGameViewModel::class.java)

        val navController = Navigation.findNavController(view)

        // Asignar el adaptador antes de cargar los datos
        videoGameAdapter = VideoGameAdapter(this)
        binding.recyclerVideogame.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerVideogame.adapter = videoGameAdapter

        // Observa los datos después de asignar el adaptador
        viewModel.getAllVideoGamesFromServer()

        viewModel.allVideoGame.observe(viewLifecycleOwner) { videos ->
            Log.i("GAME", videos.toString())
            videoGameAdapter.videogames = videos.toMutableList()
            videoGameAdapter.notifyDataSetChanged()
        }

        videoGameAdapter.setOnItemClickListener { videoGame ->
            val bundle = Bundle().apply {
                putLong("ID_VIDEO_GAME", videoGame.id)
            }
            navController.navigate(R.id.detailVideoGameFragment, bundle)
        }
    }


}