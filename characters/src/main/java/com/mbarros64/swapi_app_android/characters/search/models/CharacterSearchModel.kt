package com.mbarros64.swapi_app_android.characters.search.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

    data class CharacterSearchModel(
        val url: String?,
        val name: String?,
        val birthYear: String?
    ) : Parcelable
