package com.mbarros64.swapi_app_android.characters.details.model

import com.google.gson.annotations.SerializedName

data class CharacterHomeworldModel(
    @SerializedName("name") val name: String?,
    @SerializedName("population") val population: String?)