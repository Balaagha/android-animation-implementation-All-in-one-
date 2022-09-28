package com.example.androidimpltemplate

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import com.example.androidimpltemplate.data.database.feature.cast.model.CachedCastResponse
import com.example.androidimpltemplate.data.database.feature.cast.source.CastResponseDataSource
import com.example.androidimpltemplate.data.database.feature.movies.model.CachedMovieModel
import com.example.androidimpltemplate.data.feature.movie.repository.MoviesDataRepository
import com.example.androidimpltemplate.manager.network.NetworkStatusListenerHelper
import com.example.androidimpltemplate.manager.notfication.NotificationHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {
    @Inject
    lateinit var networkStatusListenerHelper: NetworkStatusListenerHelper

    @Inject
    lateinit var moviesDataRepository: MoviesDataRepository

    @Inject
    lateinit var castResponseDataSource: CastResponseDataSource


    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createNotificationChannel(
            this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel."
        )

        networkStatusListenerHelper.init()
        prefillData()
    }

    private fun prefillData() {
        val gson = Gson()

        val moviesToken = object : TypeToken<List<CachedMovieModel?>?>() {}.type
        val castToken = object : TypeToken<List<CachedCastResponse?>?>() {}.type

        val movies: List<CachedMovieModel> =
            gson.fromJson(String(resources.openRawResource(R.raw.movies).readBytes()), moviesToken)
        val cast: List<CachedCastResponse> =
            gson.fromJson(String(resources.openRawResource(R.raw.cast).readBytes()), castToken)

        GlobalScope.launch {
            moviesDataRepository.saveMovies(movies)
            castResponseDataSource.saveCast(cast)
        }
    }

}