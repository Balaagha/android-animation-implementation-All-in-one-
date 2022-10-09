package com.example.androidimpltemplate.ui.animationexamples.transitionwithnavgraph

import android.util.Log
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentSharedTransitionWithNavGraphABinding

class SharedTransitionWithNavGraphAFragment:BaseFragment<FragmentSharedTransitionWithNavGraphABinding>(FragmentSharedTransitionWithNavGraphABinding::inflate) {

    override fun setup() {
        binding.apply {
            imgViewExample.setOnClickListener {
                Log.d("myTag","@${this.javaClass.name} => imgViewExample clicked")
                val extras = FragmentNavigatorExtras(imgViewExample to "image_big")
                findNavController().navigate(
                    R.id.action_sharedTransitionWithNavGraphAFragment_to_sharedTransitionWithNavGraphBFragment,
                    null,
                    null,
                    extras
                )
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SharedTransitionWithNavGraphAFragment()
    }

}