package com.example.androidimpltemplate.ui.animationexamples.uifortransition

import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentExampleForTransitionBinding

class ExampleForTransitionFragment:BaseFragment<FragmentExampleForTransitionBinding>(FragmentExampleForTransitionBinding::inflate) {
    override fun setup() {}
    companion object {
        @JvmStatic
        fun newInstance() = ExampleForTransitionFragment()
    }
}