package com.mbarros64.swapi_app_android.characters.details

import com.mbarros64.swapi_app_android.characters.CharacterService
import com.mbarros64.swapi_app_android.characters.details.models.CharacterDetailsModel
import com.mbarros64.swapi_app_android.characters.details.models.response.FilmResponseModel
import com.mbarros64.swapi_app_android.characters.details.models.response.HomeworldResponseModel
import com.mbarros64.swapi_app_android.characters.details.models.response.SpeciesResponseModel
import io.reactivex.Single

class CharacterDetailsRepo(private val service: CharacterService) : CharacterDetailsContract.Repo {

    override fun getCharacterDetails(url: String): Single<CharacterDetailsModel> = service.getCharacterDetails(url)

    override fun getSpecieDetails(url: String): Single<SpeciesResponseModel> = service.getCharacterSpecies(url)

    override fun getFilmDetails(url: String): Single<FilmResponseModel> = service.getCharacterFilms(url)

    override fun getHomeworldDetails(url: String): Single<HomeworldResponseModel> = service.getCharacterHomeworld(url)

}