package com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.views

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.base.Events
import com.example.androidimpltemplate.data.database.feature.movies.model.CachedMovieModel
import com.example.androidimpltemplate.databinding.FragmentFavoritesBinding
import com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.adapter.MoviesAdapter
import com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.listener.MovieListClickListener
import com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.viewmodels.FavoriteMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMoviesFragment :
    BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate) {

    override var statusBarVisibility: Boolean? = false

    private val viewModel by viewModels<FavoriteMoviesViewModel>()

    private val favoritesAdapter by lazy {
        MoviesAdapter()
    }

    override fun setup() {
        favoritesAdapter.setListener(object : MovieListClickListener {
            override fun onMovieClicked(movie: CachedMovieModel) {
                findNavController().navigate(
                    FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToMovieDetailsFragment(
                        movie.id
                    )
                )
            }

        })
        binding.favoriteMoviesList.apply {
            adapter = favoritesAdapter
        }
        viewModel.getFavoriteMovies()
        attachObservers()
    }

    private fun attachObservers() {
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            favoritesAdapter.submitList(movies)
        }

        viewModel.events.observe(viewLifecycleOwner) { event ->
            when (event) {
                is Events.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.favoriteMoviesList.visibility = View.GONE
                }

                is Events.Done -> {
                    binding.progressBar.visibility = View.GONE
                    binding.favoriteMoviesList.visibility = View.VISIBLE
                }
            }
        }
    }

}
