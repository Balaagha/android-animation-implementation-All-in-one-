package com.example.androidimpltemplate.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.ui.login.AuthFragment

class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        supportFragmentManager.commit {
            replace(R.id.fragmentMenuContainer, MenuFragment.newInstance())
        }
    }

}