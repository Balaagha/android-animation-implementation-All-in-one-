package com.example.androidimpltemplate.ui.mainpage.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.base.Events
import com.example.androidimpltemplate.data.database.feature.movies.model.CachedMovieModel
import com.example.androidimpltemplate.databinding.FragmentPopularBinding
import com.example.androidimpltemplate.ui.mainpage.adapter.MoviesAdapter
import com.example.androidimpltemplate.ui.mainpage.listener.MovieListClickListener
import com.example.androidimpltemplate.ui.mainpage.viewmodels.AnimationViewModel
import com.example.androidimpltemplate.ui.mainpage.viewmodels.PopularMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.hypot

@AndroidEntryPoint
class PopularMoviesFragment : Fragment(R.layout.fragment_popular) {
  private var _binding: FragmentPopularBinding? = null
  private val binding get() = _binding!!

  private val viewModel by viewModels <PopularMoviesViewModel>()

  private val animationViewModel by activityViewModels <AnimationViewModel>()

  private val popularAdapter by lazy {
    MoviesAdapter()
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View {
    _binding = FragmentPopularBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    popularAdapter.setListener(object : MovieListClickListener {
      override fun onMovieClicked(movie: CachedMovieModel) {
        findNavController().navigate(PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMovieDetailsFragment(movie.id))
      }

    })
    binding.popularMoviesList.apply {
      adapter = popularAdapter
    }
    viewModel.getPopularMovies()
    attachObservers()
  }

  private fun attachObservers() {
    viewModel.movies.observe(viewLifecycleOwner) { movies ->
      popularAdapter.submitList(movies)
    }

    viewModel.events.observe(viewLifecycleOwner) { event ->
      when (event) {
        is Events.Loading -> {
          binding.progressBar.visibility = View.VISIBLE
          binding.popularMoviesList.visibility = View.GONE
        }

        is Events.Done -> {
          binding.progressBar.visibility = View.GONE
          binding.popularMoviesList.visibility = View.VISIBLE
        }
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
