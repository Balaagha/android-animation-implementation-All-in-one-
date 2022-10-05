package com.example.androidimpltemplate.ui.menu.itemsenum

enum class MenuItemsEnum(name: String) {

    AUTH_ACTIVITY("Animation example(Mini application)"),
    CONSTRAIN_LAYOUT_FADE_IN_OUT_ANIMATION("Constrain layout fade in and fade out animation Example"),
    CONSTRAIN_LAYOUT_OUT_STANDING_ANIMATION("Constrain layout out standing animation Example (keyframes)"),
    CONSTRAIN_LAYOUT_KEY_FRAMES_WITH_CONSTRAIN_SET_ANIMATION("Constrain layout key frames with constrain set animation Example"),
    BASIC_VIEW_ANIMATION_WITH_CODE_AND_XML("Basic View Animation with code and xml"),
    ;


    private var mName: String? = null

    init {
        mName = name
    }

    fun getMenuItemName(): String? {
        return mName
    }

}
