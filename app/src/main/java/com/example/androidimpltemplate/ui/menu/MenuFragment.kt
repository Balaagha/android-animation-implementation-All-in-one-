package com.example.androidimpltemplate.ui.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.databinding.FragmentMenuBinding
import com.example.androidimpltemplate.ui.menu.adapter.MenuItemsArrayAdapter
import com.example.androidimpltemplate.ui.menu.itemsenum.MenuItemsEnum
import com.example.androidimpltemplate.ui.animationexamples.ConstrainLayoutExampleFragment
import com.example.androidimpltemplate.ui.miniapplicationforanimation.login.AuthActivity

class MenuFragment : Fragment(), MenuItemsArrayAdapter.Listener {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapterScreensReachableFromMenu: MenuItemsArrayAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMenuAdapter()
    }

    private fun initMenuAdapter() {
        mAdapterScreensReachableFromMenu =
            MenuItemsArrayAdapter(requireContext(), MenuItemsEnum.values(), this)
        binding.listScreens.adapter = mAdapterScreensReachableFromMenu
    }

    override fun onMenuItemClicked(menuItem: MenuItemsEnum?) {
        requireActivity().apply {
            when (menuItem) {
                MenuItemsEnum.AUTH_ACTIVITY -> {
                    startActivity(Intent(this, AuthActivity::class.java))
                }
                MenuItemsEnum.CONSTRAIN_LAYOUT_ANIMATION_ACTIVITY -> {
                    supportFragmentManager.commit {
                        replace(
                            R.id.fragmentMenuContainer,
                            ConstrainLayoutExampleFragment.newInstance()
                        )
                        addToBackStack(null)
                    }
                }
                else -> {
                    /** // nothing impl} */
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MenuFragment()
    }

}