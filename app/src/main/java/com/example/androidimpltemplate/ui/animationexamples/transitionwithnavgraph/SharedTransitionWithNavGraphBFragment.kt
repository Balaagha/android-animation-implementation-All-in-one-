package com.example.androidimpltemplate.ui.animationexamples.transitionwithnavgraph

import android.os.Bundle
import android.transition.TransitionInflater
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentSharedTransitionWithNavGraphBBinding

class SharedTransitionWithNavGraphBFragment:BaseFragment<FragmentSharedTransitionWithNavGraphBBinding>(FragmentSharedTransitionWithNavGraphBBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun setup() {

    }

}