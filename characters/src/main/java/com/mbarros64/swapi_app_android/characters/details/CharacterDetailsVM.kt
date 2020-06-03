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
                .map {
                    val list = listOf(
                        CharacterDetailsModel.SpeciesDetails(
                            "Human", "Galactic Basic",
                            "Tatooine", "200000"),
                        CharacterDetailsModel.SpeciesDetails("Hutt", "Huttese",
                            "Alderaan", "2000000000"),
                        CharacterDetailsModel.SpeciesDetails("Wookie", "Shyriiwook", "Bespin", "60707070")
                    )
                    it.copy(specieDetails = list)
                }
                .subscribe({
                    Log.d(TAG, it.toString())
                    characterDetails.postValue(it)
                }, { handleError(it) })
                .addTo(disposable)
        }
        return characterDetails
    }

}