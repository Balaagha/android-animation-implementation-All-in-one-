package com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnimationViewModel : ViewModel() {
  val animateFavoriteEntranceLiveData = MutableLiveData(false)
  val animatePopularEntranceLiveData = MutableLiveData(false)
}
