package com.example.androidimpltemplate.ui.animationexamples

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.widget.Toast
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentViewPropertyAnimationImplBinding

class ViewPropertyAnimationImplFragment :
    BaseFragment<FragmentViewPropertyAnimationImplBinding>(FragmentViewPropertyAnimationImplBinding::inflate) {

    private val txtViewScaleXAnimation by lazy {
        ObjectAnimator.ofFloat(binding.txtViewAnimated, View.SCALE_X, 1.5F).apply {
            duration = 600L
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
        }
    }

    private val txtViewScaleYAnimation by lazy {
        ObjectAnimator.ofFloat(binding.txtViewAnimated, View.SCALE_Y, 1.5F).apply {
            duration = 600L
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
        }
    }

    private val imageViewRotationAnimation by lazy {
        ObjectAnimator.ofFloat(binding.imgViewAnimated, View.ROTATION, 1080F).apply {
            duration = 1800L
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
        }
    }

    private val imageViewRotationAnimationFromResource by lazy {
        AnimatorInflater.loadAnimator(requireContext(), R.animator.ex_one_scale_x_y).apply {
            setTarget(binding.imgViewAnimated)
        }
    }

    private val imageViewScaleAnRotationViewPropertyAnimation by lazy {
        binding.imgViewAnimated.animate().apply {
            rotation(1080F)
            xBy(130F)
            yBy(130F)
            alpha(0.5F)
            duration = 1000
            withEndAction{
                Toast.makeText(requireContext(),"Animation is end",Toast.LENGTH_LONG).show()
            }
        }
    }

    private val allAnimatorSet = AnimatorSet()


    override fun setup() {
        binding.apply {
            btnAnimatedImageIconFromResource.setOnClickListener {
                imageViewRotationAnimationFromResource.start()
            }
            btnCancelAnimatedImageIconFromResource.setOnClickListener {
                imageViewRotationAnimationFromResource.cancel()
            }
            btnAnimatedTogether.setOnClickListener {
                allAnimatorSet.playTogether(txtViewScaleXAnimation, txtViewScaleYAnimation, imageViewRotationAnimation)
                allAnimatorSet.start()
            }
            btnAnimatedTextView.setOnClickListener {
                txtViewScaleYAnimation.start()
                txtViewScaleXAnimation.start()
            }
            btnAnimatedImageIcon.setOnClickListener {
                imageViewRotationAnimation.start()
            }
            btnCancelAllAnimationSet.setOnClickListener {
                allAnimatorSet.cancel()
            }
            btnCancelImageIcon.setOnClickListener {
                imageViewRotationAnimation.cancel()
            }
            btnCancelTextView.setOnClickListener {
                txtViewScaleXAnimation.cancel()
                txtViewScaleYAnimation.cancel()
            }
            btnAnimatedImageIconWithViewAnimate.setOnClickListener {
                imageViewScaleAnRotationViewPropertyAnimation.start()
            }
            btnCancelAnimatedImageIconWithViewAnimate.setOnClickListener {
                imageViewScaleAnRotationViewPropertyAnimation.cancel()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ViewPropertyAnimationImplFragment()
    }
}