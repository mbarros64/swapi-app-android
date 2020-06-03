package com.mbarros64.swapi_app_android.characters

import com.mbarros64.swapi_app_android.archieteture.RemoteResponse
import com.mbarros64.swapi_app_android.characters.details.model.CharacterDetailsModel
import com.mbarros64.swapi_app_android.characters.details.model.CharacterFilmModel
import com.mbarros64.swapi_app_android.characters.details.model.CharacterHomeworldModel
import com.mbarros64.swapi_app_android.characters.details.model.CharacterSpeciesModel
import com.mbarros64.swapi_app_android.characters.search.models.CharacterSearchModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CharacterService {

    @GET
    fun getCharacters(@Url url: String): Single<RemoteResponse<List<CharacterSearchModel>>>

    @GET("people")
    fun searchCharacter(@Query("search") query: String): Single<RemoteResponse<List<CharacterSearchModel>>>

    @GET
    fun getCharacterDetails(@Url url: String): Single<CharacterDetailsModel>

    @GET
    fun getCharacterSpecies(@Url url: String): Single<CharacterSpeciesModel>

    @GET
    fun getCharacterHomeworld(@Url url: String): Single<CharacterHomeworldModel>

    @GET
    fun getCharacterFilms(@Url url: String): Single<CharacterFilmModel>

}