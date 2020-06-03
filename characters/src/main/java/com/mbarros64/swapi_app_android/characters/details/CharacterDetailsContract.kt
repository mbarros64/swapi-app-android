package com.mbarros64.swapi_app_android.characters.details

import com.mbarros64.swapi_app_android.characters.details.model.CharacterDetailsModel
import com.mbarros64.swapi_app_android.characters.details.model.CharacterFilmModel
import com.mbarros64.swapi_app_android.characters.details.model.CharacterSpeciesModel
import io.reactivex.Single


interface CharacterDetailsContract {
    interface Repo {
        fun getCharacterDetails(url: String): Single<CharacterDetailsModel>
        fun getSpecieDetails(url: String): Single<CharacterSpeciesModel>
        fun getFilmDetails(url: String): Single<CharacterFilmModel>
    }
}