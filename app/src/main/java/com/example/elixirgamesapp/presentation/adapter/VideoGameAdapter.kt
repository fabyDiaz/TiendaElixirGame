package com.example.elixirgamesapp.presentation.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elixirgamesapp.data.response.VideoGameResponse
import com.example.elixirgamesapp.databinding.VideogameItemBinding
import com.example.elixirgamesapp.presentation.view.VideoGameFragment
import com.squareup.picasso.Picasso


class VideoGameAdapter(context: VideoGameFragment): RecyclerView.Adapter<VideoGameAdapter.VideoGameViewHolder>() {

    var videogames = mutableListOf<VideoGameResponse>()
    private lateinit var onItemClickListener: (VideoGameResponse) -> Unit

    inner class VideoGameViewHolder(private var bindingItem: VideogameItemBinding)
        : RecyclerView.ViewHolder(bindingItem.root) {
        fun bind(videoGame: VideoGameResponse) {
            with(videoGame) {
                Picasso.get()
                    .load(videoGame.backgroundImage)
                    .centerCrop()
                    .fit()
                    .into(bindingItem.imageVideoGame)
                //Glide.with(bindingItem.imageVideoGame).load(videoGame.backgroundImage).into(bindingItem.imageVideoGame)
                bindingItem.textTitulo.text = videoGame.name
                bindingItem.textLanzamiento.text = videoGame.released
                bindingItem.textRaiting.text = videoGame.rating.toString()
            }

            bindingItem.root.setOnClickListener() {
                if (::onItemClickListener.isInitialized) {
                    onItemClickListener(videoGame)
                } else {
                    Log.e("Adapter", "Listener not initialized")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoGameViewHolder {
        val bindingItem = VideogameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoGameViewHolder(bindingItem)
    }

    override fun onBindViewHolder(holder: VideoGameViewHolder, position: Int) {
        val videoGame: VideoGameResponse = videogames[position]
        holder.bind(videoGame)
    }

    override fun getItemCount(): Int {
        return videogames.size
    }
    fun setOnItemClickListener(listener: (VideoGameResponse) -> Unit) {
        onItemClickListener = listener
    }



   /* var videoGames = mutableListOf<VideoGameResponse>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoGameAdapter.ViewHolder {
        val binding = VideogameItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoGameAdapter.ViewHolder, position: Int) {
        val videoGame = videoGames[position]
        holder.bindVideoGame(videoGame)
    }

    override fun getItemCount(): Int {
        return videoGames.size

    }

    inner class ViewHolder (private val binding: VideogameItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindVideoGame(videoGame: VideoGameResponse){
          //  binding.imageVideoGame.setImageResource(0)
           /* Picasso.get()
                .load(videoGame.backgroundImage)
                .centerCrop()
                .fit()
                .into(binding.imageVideoGame)*/

            binding.textTitulo.text = videoGame.name
            binding.textLanzamiento.text = videoGame.released
            binding.textRaiting.text = videoGame.rating.toString()

        }
    }*/
}