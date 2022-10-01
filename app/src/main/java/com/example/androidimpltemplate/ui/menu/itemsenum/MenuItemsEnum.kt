package com.example.androidimpltemplate.ui.menu.itemsenum

enum class MenuItemsEnum(name: String) {

    AUTH_ACTIVITY("Animation example(Mini application)"),
    CONSTRAIN_LAYOUT_ANIMATION_ACTIVITY("Constrain layout animation Example");


    private var mName: String? = null

    init {
        mName = name
    }

    fun getMenuItemName(): String? {
        return mName
    }

}
