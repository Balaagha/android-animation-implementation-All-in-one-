package com.example.androidimpltemplate.ui.animationexamples.transitionwithnavgraph

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.androidimpltemplate.R

class SharedTransitionWithNavGraphContainerActivity : AppCompatActivity() {

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_transition_with_nav_graph_container)
        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerInSharedActivity) as NavHostFragment
        navController = navHostFragment.navController
    }


}