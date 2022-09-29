package com.example.androidimpltemplate.ui.mainpage.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidimpltemplate.base.BaseViewModel
import com.example.androidimpltemplate.base.Events
import com.example.androidimpltemplate.data.database.feature.cast.model.CastItem
import com.example.androidimpltemplate.data.database.feature.movies.model.CachedMovieModel
import com.example.androidimpltemplate.data.feature.movie.repository.CastDataRepository
import com.example.androidimpltemplate.data.feature.movie.repository.MoviesDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val moviesDataRepository: MoviesDataRepository,
    private val castDataRepository: CastDataRepository,
) : BaseViewModel() {
    private val _movie = MutableLiveData<CachedMovieModel>()
    val movie = _movie as LiveData<CachedMovieModel>

    private val _cast = MutableLiveData<List<CastItem>>()
    val cast = _cast as LiveData<List<CastItem>>

    fun getMovieDetails(id: Int) {
        _events.value = Events.Loading
        viewModelScope.launch(Dispatchers.IO) {
            moviesDataRepository.getMovie(id).collect { movie ->
                withContext(Dispatchers.Main) {
                    _movie.value = movie
                }
            }
        }
    }

    fun getCast(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            castDataRepository.getCastDetails(id).also { cast ->
                withContext(Dispatchers.Main) {
                    _cast.value = cast.filter {
                        !it.profilePath.isNullOrEmpty()
                    }
                }
            }
        }
    }

    fun setMovieAsFavorite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesDataRepository.setFavorite(id)
        }
    }

    fun unsetMovieAsFavorite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesDataRepository.removeFavorite(id)
        }
    }
}
