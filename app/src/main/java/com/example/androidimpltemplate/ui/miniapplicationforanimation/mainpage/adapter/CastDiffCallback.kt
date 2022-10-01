package com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.androidimpltemplate.data.database.feature.cast.model.CastItem

class CastDiffCallback : DiffUtil.ItemCallback<CastItem>() {
  override fun areItemsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {
    return oldItem == newItem
  }
}