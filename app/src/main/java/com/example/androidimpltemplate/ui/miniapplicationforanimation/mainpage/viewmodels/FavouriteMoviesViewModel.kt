package com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidimpltemplate.base.BaseViewModel
import com.example.androidimpltemplate.base.Events
import com.example.androidimpltemplate.data.database.feature.movies.model.CachedMovieModel
import com.example.androidimpltemplate.data.feature.movie.repository.MoviesDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val moviesDataRepository: MoviesDataRepository
) : BaseViewModel() {


    private val _movies = MutableLiveData<List<CachedMovieModel>>()
    val movies = _movies as LiveData<List<CachedMovieModel>>

    fun getFavoriteMovies() {
        _events.value = Events.Loading
        viewModelScope.launch(Dispatchers.IO) {
            moviesDataRepository.getFavoriteMovies().collect { result ->
                withContext(Dispatchers.Main) {
                    _movies.value = result
                    _events.value = Events.Done
                }
            }
        }
    }
}
