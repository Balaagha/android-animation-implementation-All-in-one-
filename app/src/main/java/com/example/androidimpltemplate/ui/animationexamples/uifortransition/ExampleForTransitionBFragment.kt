package com.example.androidimpltemplate.ui.animationexamples.uifortransition

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.core.view.ViewCompat
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentExampleForTransitionBBinding

class ExampleForTransitionBFragment:BaseFragment<FragmentExampleForTransitionBBinding>(FragmentExampleForTransitionBBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.ex_one_fade)

        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.shared_image)

    }
    override fun setup() {
        binding.apply {
            ViewCompat.setTransitionName(imgViewExample, "imgViewExample")
//            postponeEnterTransition() // To postpone the enter transition, call Fragment.postponeEnterTransition() in the entering fragment's onViewCreated() method:
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = ExampleForTransitionBFragment()
    }
}