package com.mbarros64.swapi_app_android.characters.search.models
import com.google.gson.annotations.SerializedName
import com.swapi_app_android.starwars.dependencies.Exclude


data class CharacterResponseModel(
    @SerializedName("url") val url: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("birth_year") val birthYear: String?,
    @SerializedName("height") val heightCentimeters: String?,
    @Exclude val heightFt: Float?,
    @SerializedName("species") val species: List<String>?,
    @Exclude val languages: List<String>?,
    @Exclude val homeWorld: String?,
    @Exclude val population: String?,
    @SerializedName("films") val films: List<String>?
)