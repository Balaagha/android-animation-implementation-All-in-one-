package com.example.androidimpltemplate.menu.itemsenum

enum class MenuItemsEnum(name: String) {

    MAIN_ACTIVITY("Main Activity Example"), ;


    private var mName: String? = null

    init {
        mName = name
    }

    fun getMenuItemName(): String? {
        return mName
    }

}
