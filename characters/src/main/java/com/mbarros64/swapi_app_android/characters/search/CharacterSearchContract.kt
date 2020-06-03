package com.mbarros64.swapi_app_android.characters.search

import com.mbarros64.swapi_app_android.archieteture.RemoteResponse
import com.mbarros64.swapi_app_android.characters.search.models.CharacterSearchModel
import io.reactivex.Single

interface CharacterSearchContract {
    interface Repo{
        fun characters(url: String): Single<RemoteResponse<List<CharacterSearchModel>>>

        fun searchCharacter(query: String): Single<RemoteResponse<List<CharacterSearchModel>>>
    }
}