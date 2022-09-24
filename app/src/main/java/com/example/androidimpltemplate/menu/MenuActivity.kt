package com.example.androidimpltemplate.menu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.menu.adapter.MenuItemsArrayAdapter
import com.example.androidimpltemplate.menu.itemsenum.MenuItemsEnum
import com.example.androidimpltemplate.ui.MainActivity
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity(), MenuItemsArrayAdapter.Listener {

    private lateinit var mAdapterScreensReachableFromMenu: MenuItemsArrayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        initMenuAdapter()
    }

    private fun initMenuAdapter() {
        mAdapterScreensReachableFromMenu =
            MenuItemsArrayAdapter(this, MenuItemsEnum.values(), this)
        listScreens.adapter = mAdapterScreensReachableFromMenu
    }

    override fun onMenuItemClicked(menuItem: MenuItemsEnum?) {
        when (menuItem) {
            MenuItemsEnum.MAIN_ACTIVITY -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            else -> {// nothing impl}
            }
        }
    }

}