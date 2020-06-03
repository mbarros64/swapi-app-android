package com.mbarros64.swapi_app_android.characters

import com.mbarros64.swapi_app_android.characters.search.models.CharacterResponseModel
import com.mbarros64.swapi_app_android.archieteture.RemoteResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CharacterService {

    @GET
    fun getCharacters(@Url url: String): Single<RemoteResponse<List<CharacterResponseModel>>>

    @GET("people")
    fun searchCharacter(@Query("search") query: String): Single<RemoteResponse<List<CharacterResponseModel>>>

}