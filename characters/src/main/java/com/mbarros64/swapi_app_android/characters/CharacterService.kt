package com.mbarros64.swapi_app_android.characters

import com.mbarros64.swapi_app_android.archieteture.RemoteResponse
import com.mbarros64.swapi_app_android.characters.details.models.CharacterDetailsModel
import com.mbarros64.swapi_app_android.characters.details.models.response.FilmResponseModel
import com.mbarros64.swapi_app_android.characters.details.models.response.HomeworldResponseModel
import com.mbarros64.swapi_app_android.characters.details.models.response.SpeciesResponseModel
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
    fun getCharacterSpecies(@Url url: String): Single<SpeciesResponseModel>

    @GET
    fun getCharacterHomeworld(@Url url: String): Single<HomeworldResponseModel>

    @GET
    fun getCharacterFilms(@Url url: String): Single<FilmResponseModel>

}