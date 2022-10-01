package com.example.androidimpltemplate.ui.animationexamples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidimpltemplate.databinding.FragmentConstrainLayoutExampleBinding


class ConstrainLayoutExampleFragment : Fragment() {

    private var _binding: FragmentConstrainLayoutExampleBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConstrainLayoutExampleBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ConstrainLayoutExampleFragment()
    }
}