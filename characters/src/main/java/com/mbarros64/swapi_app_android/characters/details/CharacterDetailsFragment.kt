package com.mbarros64.swapi_app_android.characters.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.mbarros64.swapi_app_android.characters.R
import com.mbarros64.swapi_app_android.characters.search.models.CharacterSearchModel
import com.mbarros64.swapi_app_android.archieteture.BaseFragment
import com.mbarros64.swapi_app_android.characters.details.model.CharacterDetailsModel
import com.mbarros64.swapi_app_android.characters.details.ui.SpecieDetailsView
import com.mbarros64.swapi_app_android.extensions.visible
import kotlinx.android.synthetic.main.actionbar_toolbar.*
import kotlinx.android.synthetic.main.fragment_character_details.*
import org.koin.android.ext.android.inject

class CharacterDetailsFragment : BaseFragment() {
    override val layout = R.layout.fragment_character_details

    override val viewModel: CharacterDetailsVM by inject()

    private var selectedCharacter: CharacterSearchModel? = null

    companion object {
        const val TAG = "CharacterDetailsFragment"
        const val CHARACTER = "character"
        fun newInstance(character: CharacterSearchModel) =
            CharacterDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CHARACTER, character)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(CHARACTER))
                selectedCharacter = it.getParcelable(CHARACTER)
        }

        if (selectedCharacter == null) {
            showToast(R.string.something_went_wrong)
            popBack()
            return
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(toolbar, selectedCharacter?.name)

        srlDetails.isEnabled = false

        tvName.text = selectedCharacter?.name
        tvYOB.text = selectedCharacter?.birthYear

        selectedCharacter?.url?.run {
            viewModel.getCharacterDetails(this)
                .observe(this@CharacterDetailsFragment, Observer { details ->
                    handleCharacterDetails(details)
                })
        }

    }

    private fun handleCharacterDetails(details: CharacterDetailsModel) {

        tvName.text = details.name

        tvYOB.text = details.birthYear

        if (details.heightCentimeters.isValid()) {
            tvHeightLabel.visible()
            tvHeight.visible()
            tvHeight.text = details.heightCentimeters
        }
        if (details.heightFt.isValid()) {
            tvHeightFeet.visible()
            tvHeightFeet.text = details.heightFt
        }

        details.specieDetails?.run {
            tvSpeciesLabel.visible()
            llSpeciesDetails.visible()
            forEach {
                val specieLanguageView = SpecieDetailsView(parentActivity)
                specieLanguageView.setSpecieAndLanguage(it)
                llSpeciesDetails.addView(specieLanguageView)
            }
        }

    }

    override fun hideLoading() {
        srlDetails.isRefreshing = false
    }

    override fun showLoading() {
        srlDetails.isRefreshing = true
    }

    fun String?.isValid(): Boolean {
        return !this.isNullOrEmpty()
    }

}
