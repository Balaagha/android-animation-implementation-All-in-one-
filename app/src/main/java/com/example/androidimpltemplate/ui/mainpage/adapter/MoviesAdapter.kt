package com.example.androidimpltemplate.ui.mainpage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.androidimpltemplate.data.database.feature.movies.model.CachedMovieModel
import com.example.androidimpltemplate.databinding.ItemMovieBinding
import com.example.androidimpltemplate.ui.mainpage.listener.MovieListClickListener
import com.example.androidimpltemplate.utils.Constants.IMAGE_BASE

class MoviesAdapter : ListAdapter<CachedMovieModel, MoviesAdapter.MoviesViewHolder>(MoviesDiffCallback()) {

  private var listener: MovieListClickListener? = null

  fun setListener(listener: MovieListClickListener) {
    this.listener = listener
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
    val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return MoviesViewHolder(binding)
  }

  override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class MoviesViewHolder(val binding: ItemMovieBinding) :
      RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: CachedMovieModel) {
      binding.movieName.text = movie.title
      binding.movieRating.rating = movie.rating
      binding.ratingValue.text = movie.rating.toString()
      binding.movieInfo.text = movie.movieInfo
      binding.background.load(IMAGE_BASE + movie.backdropPath) {
        crossfade(true)
      }

      binding.root.setOnClickListener {
        listener?.onMovieClicked(movie)
      }
    }
  }


}