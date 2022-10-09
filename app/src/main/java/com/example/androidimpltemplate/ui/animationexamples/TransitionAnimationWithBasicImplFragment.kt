package com.example.androidimpltemplate.ui.animationexamples

import android.transition.Scene
import android.transition.TransitionManager
import androidx.fragment.app.commit
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentTransitionAnimationWithBasicImplBinding


class TransitionAnimationWithBasicImplFragment :
    BaseFragment<FragmentTransitionAnimationWithBasicImplBinding>(FragmentTransitionAnimationWithBasicImplBinding::inflate) {

    private lateinit var scene1: Scene
    private lateinit var scene2: Scene


    override fun setup() {
        binding.apply {
            btnGotoAnimationSceneScreen.setOnClickListener {
                requireActivity().apply {
                    supportFragmentManager.commit {
                        replace(
                            R.id.fragmentMenuContainer,
                            TransitionAnimationWithBasicImplSceneContainerFragment.newInstance()
                        )
                        addToBackStack(null)
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = TransitionAnimationWithBasicImplFragment()
    }
}