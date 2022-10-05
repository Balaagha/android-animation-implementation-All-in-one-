package com.example.androidimpltemplate.ui.animationexamples.uifortransition

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.core.view.ViewCompat
import androidx.fragment.app.commit
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentExampleForTransitionABinding

class ExampleForTransitionAFragment:BaseFragment<FragmentExampleForTransitionABinding>(FragmentExampleForTransitionABinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.ex_one_fade)
    }
    override fun setup() {
        binding.apply {
            ViewCompat.setTransitionName(imgViewExample, "imgViewExample")
            exampleBtn.setOnClickListener {
                activity?.apply {
                    supportFragmentManager.commit {
//                        setReorderingAllowed(true) //  To postpone a transition, you must first ensure that the fragment transaction allows reordering of fragment state changes. To allow reordering fragment state changes, call FragmentTransaction.setReorderingAllowed(), as shown in the following example:
                        addSharedElement(imgViewExample, imgViewExample.transitionName)
                        replace(
                            R.id.fragmentMenuContainer,
                            ExampleForTransitionBFragment.newInstance()
                        )
                        addToBackStack(null)
                    }
                }
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = ExampleForTransitionAFragment()
    }
}