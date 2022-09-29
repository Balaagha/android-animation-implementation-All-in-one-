package com.example.androidimpltemplate.ui.mainpage.viewmodels

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
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val moviesDataRepository: MoviesDataRepository
) : BaseViewModel() {

    private val _movies = MutableLiveData<List<CachedMovieModel>>()
    val movies = _movies as LiveData<List<CachedMovieModel>>

    fun getPopularMovies() {
        _events.value = Events.Loading

        viewModelScope.launch {
            moviesDataRepository.getPopularMovies().collect { result ->
                _events.value = Events.Done
                _movies.value = result
            }
        }
    }

    fun setMovieAsFavorite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesDataRepository.setFavorite(id)
        }
    }
}
