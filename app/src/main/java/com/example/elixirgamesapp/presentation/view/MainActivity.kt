package com.example.elixirgamesapp.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elixirgamesapp.R
import com.example.elixirgamesapp.data.local.database.AppDataBase
import com.example.elixirgamesapp.data.network.api.VideoGameService
import com.example.elixirgamesapp.data.network.retrofit.RetrofitHelper
import com.example.elixirgamesapp.data.repository.VideoGameImpl
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
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }
}