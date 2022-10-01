package com.example.androidimpltemplate.ui.login

import androidx.fragment.app.activityViewModels
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    override var statusBarVisibility: Boolean? = true

    private val viewModel by activityViewModels<AuthViewModel>()

    override fun setup() {
        binding.loginButton.setOnClickListener {
            viewModel.onLoginPressed()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}