package com.example.androidimpltemplate.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.databinding.FragmentAuthBinding
import com.example.androidimpltemplate.utils.extentions.changeStatusBarVisibility
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding


class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.changeStatusBarVisibility(false,R.color.primaryDarkColor)
        _binding = FragmentAuthBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClickListener()
        binding.root.applySystemWindowInsetsToPadding(bottom = true)
    }

    private fun initOnClickListener() {
        binding.signIn.setOnClickListener {
            showLogin()
        }
    }

    private fun showLogin() {
        parentFragmentManager.commit {
            replace(R.id.fragmentContainer, LoginFragment.newInstance())
            addToBackStack(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.changeStatusBarVisibility(true,R.color.primaryDarkColor)
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthFragment()
    }
}