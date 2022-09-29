package com.example.androidimpltemplate.ui.mainpage.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.base.Events
import com.example.androidimpltemplate.data.database.feature.movies.model.CachedMovieModel
import com.example.androidimpltemplate.databinding.FragmentFavoritesBinding
import com.example.androidimpltemplate.ui.mainpage.adapter.MoviesAdapter
import com.example.androidimpltemplate.ui.mainpage.listener.MovieListClickListener
import com.example.androidimpltemplate.ui.mainpage.viewmodels.FavoriteMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMoviesFragment : Fragment(R.layout.fragment_favorites) {
  private var _binding: FragmentFavoritesBinding? = null
  private val binding get() = _binding!!

  private val viewModel by viewModels <FavoriteMoviesViewModel>()

  private val favoritesAdapter by lazy {
    MoviesAdapter()
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View {
    _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    favoritesAdapter.setListener(object : MovieListClickListener {
      override fun onMovieClicked(movie: CachedMovieModel) {
        findNavController().navigate(
          FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToMovieDetailsFragment(movie.id))
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

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
