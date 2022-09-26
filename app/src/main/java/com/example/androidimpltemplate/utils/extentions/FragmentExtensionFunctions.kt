package com.example.androidimpltemplate.utils.extentions

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.utils.helper.isVersionHigherAndEqual
import com.example.androidimpltemplate.utils.helper.isVersionInRange


fun Fragment.changeStatusBarVisibility(isVisible: Boolean,colorResInt: Int){
    if (!isVisible) {
        this.requireActivity().window.apply {
            if(isVersionHigherAndEqual(Build.VERSION_CODES.R)){
                WindowCompat.setDecorFitsSystemWindows(this,false)
            }else {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }
    } else {
        this.requireActivity().window.apply {
            if(isVersionHigherAndEqual(Build.VERSION_CODES.R)){
                WindowCompat.setDecorFitsSystemWindows(this,true)
            }
            else if (isVersionInRange(Build.VERSION_CODES.M .. Build.VERSION_CODES.Q)) {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            }
            statusBarColor = ContextCompat.getColor(context, colorResInt)
        }
    }
}