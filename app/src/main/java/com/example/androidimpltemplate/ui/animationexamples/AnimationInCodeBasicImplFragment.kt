package com.example.androidimpltemplate.ui.animationexamples

import android.content.Intent
import android.view.animation.*
import androidx.fragment.app.commit
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentAnimationInCodeBasicImplBinding
import com.example.androidimpltemplate.ui.animationexamples.uifortransition.ExampleForTransitionAFragment
import com.example.androidimpltemplate.ui.animationexamples.uifortransition.ExampleForTransitionFragment
import com.example.androidimpltemplate.ui.animationexamples.uifortransition.ExampleScreenForTransitionActivity


class AnimationInCodeBasicImplFragment :
    BaseFragment<FragmentAnimationInCodeBasicImplBinding>(FragmentAnimationInCodeBasicImplBinding::inflate) {

    override fun setup() {
        binding.apply {
            start.setOnClickListener {
                val animationSet = getAnimationSetFromXml()
//                val animationSet = getAnimationSetProgrammatically()
                start.startAnimation(animationSet)
            }

            navigateActivityBtn.setOnClickListener {
                startActivity(
                    Intent(
                        requireContext(),
                        ExampleScreenForTransitionActivity::class.java
                    )
                )
                activity?.overridePendingTransition(R.anim.ex_one_slide_right_in, R.anim.ex_one_slide_left_out)
            }

            navigateFragmentBtn.setOnClickListener {
                activity?.apply {
                    supportFragmentManager.commit {
                        setCustomAnimations(
                            R.anim.ex_two_slide_in,
                             R.anim.ex_two_fade_out,
                             R.anim.ex_two_fade_in,
                             R.anim.ex_two_slide_out
                        )
                        replace(
                            R.id.fragmentMenuContainer,
                            ExampleForTransitionFragment.newInstance()
                        )
                        addToBackStack(null)
                    }
                }
            }

            navigateFragmentWithTransitionDefineBtn.setOnClickListener {
                activity?.apply {
                    supportFragmentManager.commit {
                        replace(
                            R.id.fragmentMenuContainer,
                            ExampleForTransitionAFragment.newInstance()
                        )
                        addToBackStack(null)
                    }
                }
            }
        }
    }

    private fun getAnimationSetFromXml(): Animation? =
        AnimationUtils.loadAnimation(context, R.anim.basic_example_view_anim)
    private fun getAnimationSetProgrammatically(): Animation? {
        val animationSet = AnimationSet(true)
        animationSet.duration = 3000

        val translate = TranslateAnimation(
            0F,
            300F,
            0F,
            300F
        )
        val rotate = RotateAnimation(0f, 360f)
        val alpha = AlphaAnimation(1f, 0f)
        val scale = ScaleAnimation(1f, 3f, 1f, 3f)
        animationSet.apply {
            addAnimation(translate)
            addAnimation(rotate)
            addAnimation(alpha)
            addAnimation(scale)
        }
        return animationSet
    }

    companion object {
        @JvmStatic
        fun newInstance() = AnimationInCodeBasicImplFragment()
    }
}