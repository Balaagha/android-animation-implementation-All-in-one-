package com.example.androidimpltemplate.menu.itemsenum

enum class MenuItemsEnum(name: String) {

    MAIN_ACTIVITY("Main Activity Example"),
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
