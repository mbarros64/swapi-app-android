package com.mbarros64.swapi_app_android.characters.search

import com.mbarros64.swapi_app_android.archieteture.BaseVM
import com.mbarros64.swapi_app_android.extensions.hide
import com.mbarros64.swapi_app_android.extensions.show
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbarros64.swapi_app_android.characters.search.models.CharacterSearchModel
import com.mbarros64.swapi_app_android.archieteture.RemoteResponse
import io.reactivex.Single
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class CharacterSearchVM(private val repo: CharacterSearchContract.Repo?) : BaseVM() {

    private val _characters = MutableLiveData<List<CharacterSearchModel>>()
    val characters: LiveData<List<CharacterSearchModel>> by lazy { _characters }

    private var nextPageUrl: String? = ""
    private var processing: Boolean = false


    private val _paginationLoading = MutableLiveData<Boolean>()
    val paginationLoading: LiveData<Boolean> by lazy { _paginationLoading }

    private val initialAPI = "people"

    private val TAG = "CharacterSearchVM"

    fun initialLoad(enforce: Boolean = false) {
        if (!enforce && _characters.value != null && !_characters.value.isNullOrEmpty()) return

        _loading.show()
        getCharacters(url = initialAPI, resetItems = enforce)
    }

    private fun getCharacters(url: String, resetItems: Boolean) {
        if (processing) return

        processing = true
        if (repo != null) {
            handleCharactersObs(repo.characters(url), resetItems)
        }
    }

    private fun handleCharactersObs(charactersObs: Single<RemoteResponse<List<CharacterSearchModel>>>, resetItems: Boolean) {
        charactersObs
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { response ->
                nextPageUrl = response.next
                return@map response.results
            }

            .map { searchModels ->
                appendOrSetResults(resetItems, _characters.value, searchModels)
            }
            .subscribe({
                _loading.hide()
                _paginationLoading.hide()
                _characters.postValue(it)
                processing = false
            }, {
                handleError(it)
                processing = false
            })
            .addTo(disposable)
    }

    private fun appendOrSetResults(resetItems: Boolean,
                                   existingData: List<CharacterSearchModel>?,
                                   newData: List<CharacterSearchModel>): List<CharacterSearchModel> {
        val finalData = mutableListOf<CharacterSearchModel>()
        if (!resetItems && !existingData.isNullOrEmpty()) {
            finalData.addAll(existingData)
            finalData.addAll(newData)
        } else
            finalData.addAll(newData)
        return finalData
    }

    fun loadNextPage() {
        nextPageUrl?.run {
            _paginationLoading.show()
            getCharacters(this, false)
        }
    }

    fun searchCharacter(query: String?) {
        if (query.isNullOrEmpty()) return

        _loading.show()
        if (repo != null) {
            handleCharactersObs(repo.searchCharacter(query), true)
        }
    }

    fun refreshCharacters() {
        _loading.show()
        getCharacters(url = initialAPI, resetItems = true)
    }
}




