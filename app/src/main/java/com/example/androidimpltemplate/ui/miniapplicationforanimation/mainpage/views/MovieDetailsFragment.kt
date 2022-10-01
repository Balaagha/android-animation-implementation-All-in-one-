package com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.views

import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.BlurTransformation
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.data.database.feature.movies.model.CachedMovieModel
import com.example.androidimpltemplate.databinding.FragmentDetailsBinding
import com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.adapter.CastAdapter
import com.example.androidimpltemplate.ui.miniapplicationforanimation.mainpage.viewmodels.MovieDetailsViewModel
import com.example.androidimpltemplate.utils.Constants.IMAGE_BASE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

  override var statusBarVisibility: Boolean? = false

  private val args: MovieDetailsFragmentArgs by navArgs()

  private val viewModel by viewModels <MovieDetailsViewModel>()

  private val castAdapter by lazy {
    CastAdapter()
  }

  override fun setup() {
    binding.castList.apply {
      adapter = castAdapter
      layoutManager =
        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    args.movieId.also {
      viewModel.getMovieDetails(it)
      viewModel.getCast(it)
    }
    attachObservers()
  }

  private fun attachObservers() {
    viewModel.movie.observe(viewLifecycleOwner) { movie ->
      renderUi(movie)
    }

    viewModel.cast.observe(viewLifecycleOwner) { cast ->
      castAdapter.submitList(cast)
    }
  }

  private fun renderUi(movie: CachedMovieModel) {
    binding.backdrop.load(IMAGE_BASE + movie.backdropPath) {
      crossfade(true)
      transformations(BlurTransformation(requireContext()))
    }
    binding.poster.load(IMAGE_BASE + movie.posterPath) {
      crossfade(true)
    }

    binding.title.text = movie.title
    binding.summary.text = movie.overview
    binding.ratingValue.text = movie.rating.toString()
    binding.movieRating.rating = movie.rating

    binding.addToFavorites.apply {
      icon = if (movie.isFavorite) {
        getDrawable(requireContext(), R.drawable.ic_baseline_favorite_24)
      } else {
        getDrawable(requireContext(), R.drawable.ic_baseline_favorite_border_24)
      }
      text = if (movie.isFavorite) {
        getString(R.string.remove_from_favorites)
      } else {
        getString(R.string.add_to_favorites)
      }
      setOnClickListener {
//        changeStatusBarVisibility(isVisible = false, defaultStatusBarColor)

        if (movie.isFavorite) {
          viewModel.unsetMovieAsFavorite(movie.id)
        } else {
          viewModel.setMovieAsFavorite(movie.id)
        }
      }
    }
  }
}
