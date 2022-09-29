package com.example.androidimpltemplate.ui.mainpage

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.databinding.ActivityMainPageBinding
import com.example.androidimpltemplate.ui.mainpage.viewmodels.AnimationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPageActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movieDetailsFragment -> binding.bottomNav.visibility = View.GONE
                else -> binding.bottomNav.visibility = View.VISIBLE
            }
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.auth_main_enter, R.anim.auth_main_exit)
    }

}