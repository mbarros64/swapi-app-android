package com.mbarros64.swapi_app_android.characters

import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import com.mbarros64.swapi_app_android.characters.search.CharacterSearchRepo
import com.mbarros64.swapi_app_android.characters.search.CharacterSearchVM
import com.mbarros64.swapi_app_android.characters.details.CharacterDetailsRepo
import com.mbarros64.swapi_app_android.characters.details.CharacterDetailsVM
import com.mbarros64.swapi_app_android.characters.search.CharacterSearchContract
import com.mbarros64.swapi_app_android.characters.details.CharacterDetailsContract
import org.koin.androidx.viewmodel.dsl.viewModel


object CharacterDH {

    fun init() {
        loadKoinModules(characterDetailsModule(), characterSearchModule(), characterModule())
    }

    //Details module
    private fun characterDetailsModule(): Module = module {
        viewModel { CharacterDetailsVM(get()) }
        single { characterDetailsContract(get()) }
    }

    private fun characterDetailsContract(service: CharacterService)
            : CharacterDetailsContract.Repo = CharacterDetailsRepo(service)

    //Search module
    private fun characterSearchModule(): Module = module {
        viewModel { CharacterSearchVM(get()) }
        single { characterSearchContract(get()) }
    }

    private fun characterSearchContract(service: CharacterService)
            : CharacterSearchContract.Repo = CharacterSearchRepo(service)


    //Character module
    private fun characterModule(): Module = module {
        single { characterService(get()) }
    }

    private fun characterService(retrofit: Retrofit): CharacterService = retrofit.create()

}