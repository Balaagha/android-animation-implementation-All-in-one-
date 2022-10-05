package com.example.androidimpltemplate.ui.animationexamples.uifortransition

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.databinding.ActivityExampleScreenForTransitionBinding

class ExampleScreenForTransitionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExampleScreenForTransitionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExampleScreenForTransitionBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.ex_one_slide_left_in,R.anim.ex_one_slide_right_out)
//        overridePendingTransition(R.anim.ex_two_fade_in,R.anim.ex_two_slide_out)
    }

}