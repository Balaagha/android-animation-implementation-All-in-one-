package com.example.androidimpltemplate.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.databinding.ActivityAuthBinding
import com.example.androidimpltemplate.ui.mainpage.MainPageActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private val viewModel by viewModels<AuthViewModel>()

    private val binding by lazy {
        ActivityAuthBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, AuthFragment.newInstance())
        }

        viewModel.showLogin.observe(this) {
            showLogin()
        }
        viewModel.showSignUp.observe(this) {
            showSignup()
        }
        viewModel.showMain.observe(this) {
            startActivity(Intent(this, MainPageActivity::class.java))
            overridePendingTransition(R.anim.auth_main_enter, R.anim.auth_main_exit)
        }
    }

    private fun showLogin() {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, LoginFragment.newInstance())
            addToBackStack(null)
        }
    }

    private fun showSignup() {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, SignUpFragment.newInstance())
            addToBackStack(null)
        }
    }


}