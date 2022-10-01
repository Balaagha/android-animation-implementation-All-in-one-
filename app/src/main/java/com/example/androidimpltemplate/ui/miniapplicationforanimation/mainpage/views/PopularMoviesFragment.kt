package com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.views

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.base.Events
import com.example.androidimpltemplate.data.database.feature.movies.model.CachedMovieModel
import com.example.androidimpltemplate.databinding.FragmentPopularBinding
import com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.adapter.MoviesAdapter
import com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.listener.MovieListClickListener
import com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.viewmodels.AnimationViewModel
import com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.viewmodels.PopularMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment :
    BaseFragment<FragmentPopularBinding>(FragmentPopularBinding::inflate) {

    override var statusBarVisibility: Boolean? = false

    private val viewModel by viewModels<PopularMoviesViewModel>()

    private val animationViewModel by activityViewModels<AnimationViewModel>()

    private val popularAdapter by lazy {
        MoviesAdapter()
    }

    override fun setup() {
        popularAdapter.setListener(object : MovieListClickListener {
            override fun onMovieClicked(movie: CachedMovieModel) {
                findNavController().navigate(
                    PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMovieDetailsFragment(
                        movie.id
                    )
                )
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

}
