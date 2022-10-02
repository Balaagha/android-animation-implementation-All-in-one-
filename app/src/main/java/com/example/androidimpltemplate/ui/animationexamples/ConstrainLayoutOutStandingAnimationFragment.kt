package com.example.androidimpltemplate.ui.animationexamples

import android.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionManager
import android.view.animation.LinearInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentConstrainLayoutOutStandingAnimationBinding


class ConstrainLayoutOutStandingAnimationFragment : BaseFragment<FragmentConstrainLayoutOutStandingAnimationBinding>(FragmentConstrainLayoutOutStandingAnimationBinding::inflate) {

    override fun setup() {
        binding.apply {
            start.setOnClickListener {
                val set = ConstraintSet()
                set.clone(requireContext(), R.layout.fragment_constrain_layout_out_standing_animation_key_frames)
                set.applyTo(rootLayout)

                val transition: Transition = ChangeBounds()
                transition.interpolator = LinearInterpolator() // BounceInterpolator() // OvershootInterpolator()
                transition.duration = 600
                TransitionManager.beginDelayedTransition(rootLayout, transition)
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = ConstrainLayoutOutStandingAnimationFragment()
    }
}