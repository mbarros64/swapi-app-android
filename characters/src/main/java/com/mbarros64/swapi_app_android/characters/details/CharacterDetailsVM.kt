package com.mbarros64.swapi_app_android.characters.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbarros64.swapi_app_android.archieteture.BaseVM
import com.mbarros64.swapi_app_android.characters.details.model.CharacterDetailsModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class CharacterDetailsVM(private val repo: CharacterDetailsContract.Repo) : BaseVM() {

    private val characterDetails = MutableLiveData<CharacterDetailsModel>()

    private val TAG = "CharacterDetailsVM"

    fun getCharacterDetails(url: String): LiveData<CharacterDetailsModel> {
        if (characterDetails.value == null) {
            repo.getCharacterDetails(url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe({
                    Log.d(TAG, it.toString())
                }, { handleError(it) })
                .addTo(disposable)
        }
        return characterDetails
    }

}