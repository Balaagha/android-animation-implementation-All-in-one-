package com.example.androidimpltemplate.ui.animationexamples

import android.transition.Scene
import android.transition.TransitionManager
import android.widget.Button
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentTransitionAnimationWithBasicImplSceneContainerBinding


class TransitionAnimationWithBasicImplSceneContainerFragment :
    BaseFragment<FragmentTransitionAnimationWithBasicImplSceneContainerBinding>(FragmentTransitionAnimationWithBasicImplSceneContainerBinding::inflate) {

    private lateinit var scene1: Scene
    private lateinit var scene2: Scene


    override fun setup() {
        binding.apply {
            scene1 = Scene.getSceneForLayout(binding.root,R.layout.fragment_transition_animation_with_basic_impl_scene_one, requireContext())
            scene2 = Scene.getSceneForLayout(binding.root,R.layout.fragment_transition_animation_with_basic_impl_scene_two, requireContext())

            TransitionManager.go(scene1)

            val btnGoToSceneTwo = scene1.sceneRoot.findViewById<Button>(R.id.btnGoToSceneTwo)
            btnGoToSceneTwo.setOnClickListener {
                TransitionManager.go(scene2)
                val exitFromSceneTwo = scene2.sceneRoot.findViewById<Button>(R.id.btnExitFromSceneTwo)
                exitFromSceneTwo.setOnClickListener {
                    TransitionManager.go(scene1)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = TransitionAnimationWithBasicImplSceneContainerFragment()
    }
}

