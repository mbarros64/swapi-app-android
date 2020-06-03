package com.mbarros64.swapi_app_android.characters.search

import com.mbarros64.swapi_app_android.characters.CharacterService
import com.mbarros64.swapi_app_android.characters.search.models.CharacterResponseModel
import com.swapi_app_android.starwars.archieteture.RemoteResponse
import io.reactivex.Single

class CharacterSearchRepo(private val service: CharacterService) : CharacterSearchContract.Repo {

    override fun characters(url: String)
            : Single<RemoteResponse<List<CharacterResponseModel>>> = service.getCharacters(url)

    override fun searchCharacter(query: String)
            : Single<RemoteResponse<List<CharacterResponseModel>>> = service.searchCharacter(query)

}