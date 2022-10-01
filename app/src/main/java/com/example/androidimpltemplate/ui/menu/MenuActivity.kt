package com.example.androidimpltemplate.ui.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.androidimpltemplate.R

class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        supportFragmentManager.commit {
            replace(R.id.fragmentMenuContainer, MenuFragment.newInstance())
        }
    }

}