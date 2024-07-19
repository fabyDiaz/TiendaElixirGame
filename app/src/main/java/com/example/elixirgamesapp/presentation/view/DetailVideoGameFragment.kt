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
import androidx.navigation.fragment.findNavController
import com.example.elixirgamesapp.R
import com.example.elixirgamesapp.data.local.database.AppDataBase
import com.example.elixirgamesapp.data.network.api.VideoGameService
import com.example.elixirgamesapp.data.network.retrofit.RetrofitHelper
import com.example.elixirgamesapp.data.repository.VideoGameImpl
import com.example.elixirgamesapp.databinding.FragmentDetailVideoGameBinding
import com.example.elixirgamesapp.domain.VideoGameUseCase
import com.example.elixirgamesapp.presentation.viewmodel.DetailViewModel
import com.example.elixirgamesapp.presentation.viewmodel.DetailViewModelFactory
import com.squareup.picasso.Picasso

class DetailVideoGameFragment : Fragment() {
    private var _binding: FragmentDetailVideoGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val idVideoGame = it.getLong("ID_VIDEO_GAME", -1)
            if (idVideoGame != -1L) {
                setupViewModel(idVideoGame)
            } else {
                // Manejar el error apropiadamente
                Log.e("DetailVideoGameFragment", "ID_VIDEO_GAME no encontrado en los argumentos")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailVideoGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupViewModel(idVideoGame: Long) {
        val dataBase = AppDataBase.getDatabase(requireContext().applicationContext)
        val apiService = RetrofitHelper.getRetrofit().create(VideoGameService::class.java)
        val repository = VideoGameImpl(apiService, dataBase.videoGameDao())
        val useCase = VideoGameUseCase(repository)
        val detailViewModelFactory = DetailViewModelFactory(useCase)
        viewModel = ViewModelProvider(this, detailViewModelFactory).get(DetailViewModel::class.java)

        viewModel.getDetailVideoGameById(idVideoGame)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.volverJuegos.setOnClickListener{findNavController().navigate(R.id.videoGameFragment)}

        viewModel.videoGamedetails.observe(viewLifecycleOwner) { details ->
            Log.i("DETAIL", details.toString())
            Picasso.get()
                .load(details.backgroundImage)
                .centerCrop()
                .fit()
                .into(binding.backgroundImage)
            binding.price.text = details.price.toString()
            binding.volverJuegos.title = details.name
            binding.txtanho.text = details.released
            binding.txtgenero.text = details.genres
            binding.txtduracion.text = details.playtime.toString()
            binding.txtplataformas.text = details.platforms
            binding.ratingBar.rating = details.rating.toFloat()

            binding.button.setOnClickListener {
                sendEmailVideoJurgo(details.name, details.id)
            }
        }
    }

    private fun sendEmailVideoJurgo(nameVideoGame: String, idVideoGame: Long){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("sdigitalys@gamil.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Quiero un video Juego")
        intent.putExtra(
            Intent.EXTRA_TEXT, "Hola, vi el video juego ${nameVideoGame} de código ${idVideoGame} y me gustaría " +
                "que me contactaran al este correo o al siguiente número \n " +
                "quedo atento")
        if(intent.resolveActivity(requireContext().packageManager)!=null){
            startActivity(Intent.createChooser(intent, "Enviar por correo"))
        }else{
            Toast.makeText(
                requireContext(),
                "Tienes que tener instalada la aplicación de correo",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
