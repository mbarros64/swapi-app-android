package com.mbarros64.swapi_app_android.characters.details

import com.mbarros64.swapi_app_android.characters.details.model.CharacterDetailsModel
import io.reactivex.Single


interface CharacterDetailsContract {
    interface Repo {
        fun getCharacterDetails(url: String): Single<CharacterDetailsModel>
    }
}