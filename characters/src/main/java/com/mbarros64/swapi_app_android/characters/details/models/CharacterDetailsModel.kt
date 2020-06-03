package com.mbarros64.swapi_app_android.characters.details.models

import com.google.gson.annotations.SerializedName
import com.mbarros64.swapi_app_android.dependencies.Exclude

data class CharacterDetailsModel(
    @SerializedName("url") val url: String,
    @SerializedName("name") val name: String,
    @SerializedName("birth_year") val birthYear: String,
    @SerializedName("height") val heightCentimeters: String,
    @Exclude val heightFtInches: Pair<String, String>?,

    @SerializedName("species") val speciesUrl: List<String>,
    @Exclude val specieDetails: List<SpeciesDetailsModel>?,

    @SerializedName("films") val filmUrls: List<String>,
    @Exclude val filmDetails: List<FilmDetailsModel>?
) {

}
