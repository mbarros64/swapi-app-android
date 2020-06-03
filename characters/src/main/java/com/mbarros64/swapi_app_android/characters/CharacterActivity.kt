package com.mbarros64.swapi_app_android.characters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mbarros64.swapi_app_android.characters.details.CharacterDetailsFragment
import com.mbarros64.swapi_app_android.characters.search.CharacterSearchFragment
import com.mbarros64.swapi_app_android.characters.search.models.CharacterSearchModel

private val loadCharacterDependencies by lazy { CharacterDH.init() }
private fun injectCharacterDependencies() = loadCharacterDependencies

class CharacterActivity : AppCompatActivity(), CharacterSearchFragment.CharacterNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        injectCharacterDependencies()

        navigateTo(CharacterSearchFragment.newInstance(), CharacterSearchFragment.TAG)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1)
            super.onBackPressed()
        else finish()
    }

    override fun showCharacterDetails(character: CharacterSearchModel) {
        navigateTo(CharacterDetailsFragment.newInstance(character), CharacterDetailsFragment.TAG)
    }

    private fun navigateTo(fragment: Fragment, tag: String) {
        if (supportFragmentManager.findFragmentByTag(tag) != null) return
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flContainer, fragment, tag)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
}
