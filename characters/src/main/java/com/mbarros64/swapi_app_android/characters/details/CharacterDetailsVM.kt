package com.mbarros64.swapi_app_android.characters.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbarros64.swapi_app_android.archieteture.BaseVM
import com.mbarros64.swapi_app_android.characters.details.model.CharacterDetailsModel
import com.mbarros64.swapi_app_android.characters.details.model.OpeningCrawl
import com.mbarros64.swapi_app_android.characters.details.model.ReleaseDate
import com.mbarros64.swapi_app_android.characters.details.model.Title
import com.mbarros64.swapi_app_android.extensions.hide
import com.mbarros64.swapi_app_android.extensions.show
import io.reactivex.Flowable.fromIterable
import io.reactivex.Observable.fromIterable
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.util.*

class CharacterDetailsVM(private val repo: CharacterDetailsContract.Repo) : BaseVM() {

    private val characterDetails = MutableLiveData<CharacterDetailsModel>()

    fun getCharacterDetails(url: String): LiveData<CharacterDetailsModel> {
        if (characterDetails.value == null) {
            _loading.show()
            repo.getCharacterDetails(url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe({
                    loadCharacterAdditionalDetails(it)
                    characterDetails.postValue(it)
                }, { handleError(it) })
                .addTo(disposable)
        }
        return characterDetails
    }

    private fun loadCharacterAdditionalDetails(model: CharacterDetailsModel) {
        Observable.fromIterable(model.films)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .flatMapSingle { repo.getFilmDetails(it) }
            .toList()
            .map { filmsModel ->
                val currentValue = characterDetails.value
                val films = mutableListOf<Triple<Title, ReleaseDate, OpeningCrawl>>()
                filmsModel.forEach { film ->
                    films.add(Triple(film.title, film.releaseDate, film.openingCrawl))
                }
                return@map currentValue?.copy(filmDetails = films)
            }.subscribe({
                _loading.hide()
                characterDetails.postValue(it)
            }, { handleError(it) })
            .addTo(disposable)

    }

    /*
    * .map {
                        val list = listOf(
                                CharacterDetailsModel.SpeciesDetails(
                                        "Human", "Galactic Basic",
                                        "Tatooine", "200000"),
                                CharacterDetailsModel.SpeciesDetails("Hutt", "Huttese",
                                        "Alderaan", "2000000000"),
                                CharacterDetailsModel.SpeciesDetails("Wookie", "Shyriiwook", "Bespin", "60707070")
                        )
                        val film = listOf(
                                Triple("A New Hope", "1977-05-25",
                                        "It is a period of civil war.\n\nRebel spaceships, striking\n\nfrom a hidden base, have won\n\ntheir first victory against\n\nthe evil Galactic Empire.\n\n\n\nDuring the battle, Rebel\n\nspies managed to steal secret\r\nplans to the Empire's\n\nultimate weapon, the DEATH\n\nSTAR, an armored space\n\nstation with enough power\n\nto destroy an entire planet.\n\n\n\nPursued by the Empire's\n\nsinister agents, Princess\n\nLeia races home aboard her\n\nstarship, custodian of the\n\nstolen plans that can save her\n\npeople and restore\n\nfreedom to the galaxy...."),
                                Triple("A New Hope", "1977-05-25",
                                        "It is a period of civil war.\n\nRebel spaceships, striking\n\nfrom a hidden base, have won\n\ntheir first victory against\n\nthe evil Galactic Empire.\n\n\n\nDuring the battle, Rebel\n\nspies managed to steal secret\r\nplans to the Empire's\n\nultimate weapon, the DEATH\n\nSTAR, an armored space\n\nstation with enough power\n\nto destroy an entire planet.\n\n\n\nPursued by the Empire's\n\nsinister agents, Princess\n\nLeia races home aboard her\n\nstarship, custodian of the\n\nstolen plans that can save her\n\npeople and restore\n\nfreedom to the galaxy...."))
                        it.copy(specieDetails = list, filmDetails = film)
                    }
    *
    * */

}