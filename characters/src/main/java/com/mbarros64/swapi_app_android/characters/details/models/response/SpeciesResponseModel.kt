package com.mbarros64.swapi_app_android.characters.details.models.response

import com.google.gson.annotations.SerializedName

data class SpeciesResponseModel(
    @SerializedName("name") val name: String,
    @SerializedName("language") val language: String,
    @SerializedName("homeworld") val homeworldUrl: String)