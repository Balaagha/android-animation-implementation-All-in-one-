package com.example.androidimpltemplate.ui.animationexamples

import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentConstrainLayoutKeyFramesAnimationWithConstrainSetBinding


class ConstrainLayoutKeyFramesAnimationWithConstraintSetFragment :
    BaseFragment<FragmentConstrainLayoutKeyFramesAnimationWithConstrainSetBinding>(
        FragmentConstrainLayoutKeyFramesAnimationWithConstrainSetBinding::inflate
    ) {

    private var isDetailLayout = false

    override fun setup() {
        binding.apply {
            constraintLayout.setOnClickListener {
                if (isDetailLayout)
                    swapFrames(R.layout.fragment_constrain_layout_key_frames_animation_with_constrain_set) // switch to default layout
                else
                    swapFrames(R.layout.fragment_constrain_layout_key_frames_animation_with_constrain_set_second_keyframe) // switch to detail layout
            }
        }
    }

    private fun swapFrames(layoutId: Int) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(requireContext(), layoutId)

        val transition = ChangeBounds()
        transition.interpolator =
            AnticipateOvershootInterpolator(1.0f) // LinearInterpolator() // BounceInterpolator() // OvershootInterpolator()
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(binding.constraintLayout, transition)

        constraintSet.applyTo(binding.constraintLayout)

        isDetailLayout = !isDetailLayout

    }

    companion object {
        @JvmStatic
        fun newInstance() = ConstrainLayoutKeyFramesAnimationWithConstraintSetFragment()
    }
}