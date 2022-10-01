package com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.androidimpltemplate.data.database.feature.movies.model.CachedMovieModel

class MoviesDiffCallback : DiffUtil.ItemCallback<CachedMovieModel>() {
  override fun areItemsTheSame(oldItem: CachedMovieModel, newItem: CachedMovieModel): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: CachedMovieModel, newItem: CachedMovieModel): Boolean {
    return oldItem == newItem
  }

}