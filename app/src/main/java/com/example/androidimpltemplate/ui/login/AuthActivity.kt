package com.example.androidimpltemplate.ui.login

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.databinding.ActivityAuthBinding
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