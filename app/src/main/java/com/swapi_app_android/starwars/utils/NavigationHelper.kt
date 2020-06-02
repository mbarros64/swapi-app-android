package com.swapi_app_android.starwars.utils


import android.content.Intent

private const val PACKAGE_NAME = "com.karntrehan.starwars"

fun intentTo(navigatableActivity: NavigatableActivity): Intent {
    return Intent(Intent.ACTION_VIEW).setClassName(
        PACKAGE_NAME,
        navigatableActivity.className
    )
}

interface NavigatableActivity {
    val className: String
}

object Activities {

    object Characters : NavigatableActivity {
        override val className = "$PACKAGE_NAME.characters.CharacterActivity"
    }

}