package com.example.androidimpltemplate.ui.login

import androidx.fragment.app.activityViewModels
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentAuthBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding


@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    override var statusBarVisibility: Boolean? = false

    override fun setup() {
        binding.root.applySystemWindowInsetsToPadding(bottom = true)
        initOnClickListener()
    }

    private val viewModel by activityViewModels<AuthViewModel>()

    private fun initOnClickListener() {
        binding.signIn.setOnClickListener {
            viewModel.onSignInPressed()
        }
        binding.newSignUp.setOnClickListener {
            viewModel.onNewUserPressed()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthFragment()
    }
}