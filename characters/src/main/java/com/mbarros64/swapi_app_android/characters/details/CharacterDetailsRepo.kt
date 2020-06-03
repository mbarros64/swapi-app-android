package com.mbarros64.swapi_app_android.characters.details

import com.mbarros64.swapi_app_android.characters.CharacterService
import com.mbarros64.swapi_app_android.characters.details.model.CharacterDetailsModel
import io.reactivex.Single

class CharacterDetailsRepo(private val service: CharacterService) : CharacterDetailsContract.Repo {

    override fun getCharacterDetails(url: String): Single<CharacterDetailsModel> = service.getCharacterDetails(url)


}