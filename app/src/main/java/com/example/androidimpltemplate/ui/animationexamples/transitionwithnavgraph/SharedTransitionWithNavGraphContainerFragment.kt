package com.example.androidimpltemplate.ui.animationexamples.transitionwithnavgraph

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentSharedTransitionWithNavGraphContainerBinding


class SharedTransitionWithNavGraphContainerFragment :
    BaseFragment<FragmentSharedTransitionWithNavGraphContainerBinding>(
        FragmentSharedTransitionWithNavGraphContainerBinding::inflate
    ) {

    private lateinit var navController: NavController

    override fun setup() {
        binding.apply {
            val navHostFragment =
                childFragmentManager.findFragmentById(R.id.fragmentContainerInSharedFragment) as NavHostFragment
            navController = navHostFragment.navController

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SharedTransitionWithNavGraphContainerFragment()
    }

}