package com.mbarros64.swapi_app_android.characters.search

import com.mbarros64.swapi_app_android.characters.CharacterService
import com.mbarros64.swapi_app_android.archieteture.RemoteResponse
import com.mbarros64.swapi_app_android.characters.search.models.CharacterSearchModel
import io.reactivex.Single

class CharacterSearchRepo(private val service: CharacterService) : CharacterSearchContract.Repo {

    override fun characters(url: String)
            : Single<RemoteResponse<List<CharacterSearchModel>>> = service.getCharacters(url)

    override fun searchCharacter(query: String)
            : Single<RemoteResponse<List<CharacterSearchModel>>> = service.searchCharacter(query)

}