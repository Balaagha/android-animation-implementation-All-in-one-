package com.example.androidimpltemplate.ui.login

import androidx.fragment.app.activityViewModels
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentSignupBinding

class SignUpFragment : BaseFragment<FragmentSignupBinding>(FragmentSignupBinding::inflate) {

    override var statusBarVisibility: Boolean? = true

    private val viewModel by activityViewModels<AuthViewModel>()

    override fun setup() {
        binding.signUpButton.setOnClickListener {
            viewModel.onSignupPressed()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignUpFragment()
    }

}