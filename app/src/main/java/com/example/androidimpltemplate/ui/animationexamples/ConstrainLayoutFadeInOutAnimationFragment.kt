package com.example.androidimpltemplate.ui.animationexamples

import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.androidimpltemplate.databinding.FragmentConstrainLayoutFadeInOutAnimationBinding


class ConstrainLayoutFadeInOutAnimationFragment : Fragment() {

    private var _binding: FragmentConstrainLayoutFadeInOutAnimationBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConstrainLayoutFadeInOutAnimationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            start.setOnClickListener {
                start.isVisible = false
                fadeInViews(image, username, password, login, duration = 5000)
                image.fadeInWithDelayed(300,200)
                username.fadeInWithDelayed(600,200)
                password.fadeInWithDelayed(900,200)
                login.fadeInWithDelayed(1200,200)
            }
            login.setOnClickListener {

            }
        }
    }

    private fun fadeInViews(
        vararg viewList: View,
        duration: Long = 300,
        animationEndAction: () -> Unit = {}
    ) {
        viewList.forEachIndexed { index, view ->
            view.fadeIn(duration)
            if (viewList.size - 1 == index) {
                Handler(Looper.getMainLooper()).postDelayed({
                    animationEndAction.invoke()
                }, duration)
            }
        }
    }

    fun View.fadeInWithDelayed(haltTime: Long = 0L, fadeDuration: Long = 400) {
        this.visibility = View.INVISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            this.startAnimation(AlphaAnimation(0F, 1F).apply {
                duration = fadeDuration
                fillAfter = false
            })
            this.visibility = View.VISIBLE
        }, haltTime)
    }

    fun View.fadeIn(duration: Long = 300, animationEndAction: () -> Unit = {}) {
        alpha = 0f
        visibility = View.VISIBLE
        val animation = animate()
        animation
            .alpha(1f)
            .setDuration(duration)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {}
                override fun onAnimationEnd(animation: Animator?) {
                    animationEndAction.invoke()
                }

                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationRepeat(animation: Animator?) {}
            })
    }

    fun View.fadeOut(duration: Long = 300, animationEndAction: () -> Unit = {}) {
        animate()
            .alpha(0f)
            .setDuration(duration)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {}
                override fun onAnimationEnd(animation: Animator?) {
                    visibility = View.GONE
                    animationEndAction.invoke()
                }

                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationRepeat(animation: Animator?) {}
            })
    }


    companion object {
        @JvmStatic
        fun newInstance() = ConstrainLayoutFadeInOutAnimationFragment()
    }
}