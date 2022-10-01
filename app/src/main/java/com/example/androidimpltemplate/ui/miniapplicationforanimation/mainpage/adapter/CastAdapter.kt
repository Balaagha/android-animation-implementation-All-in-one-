package com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.androidimpltemplate.data.database.feature.cast.model.CastItem
import com.example.androidimpltemplate.databinding.ItemCastBinding
import com.example.androidimpltemplate.utils.Constants.IMAGE_BASE

class CastAdapter : ListAdapter<CastItem, CastAdapter.CastViewHolder>(CastDiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
    val binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return CastViewHolder(binding)
  }

  override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class CastViewHolder(val binding: ItemCastBinding) :
      RecyclerView.ViewHolder(binding.root) {
    fun bind(cast: CastItem) {
      binding.castImage.load(IMAGE_BASE + cast.profilePath) {
        crossfade(true)
        transformations(CircleCropTransformation())
      }
    }

  }
}