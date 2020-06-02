package com.swapi_app_android.starwars.extensions


import android.content.Intent



private const val PACKAGE_NAME = "com.mbarros64.swapi_app_android"

fun intentTo(navigatableActivity: NavigatableActivity): Intent {
    return Intent(Intent.ACTION_VIEW).setClassName(
        PACKAGE_NAME,
        navigatableActivity.className)
}

interface NavigatableActivity {
    val className: String
}

object Activities {

    object Characters :
        NavigatableActivity {
        override val className = "$PACKAGE_NAME.characters.CharacterActivity"
    }

}