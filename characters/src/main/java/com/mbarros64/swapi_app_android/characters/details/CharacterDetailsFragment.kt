package com.mbarros64.swapi_app_android.characters.details

import android.os.Bundle
import android.view.View
import com.mbarros64.swapi_app_android.characters.R
import com.mbarros64.swapi_app_android.characters.search.models.CharacterSearchModel
import com.mbarros64.swapi_app_android.archieteture.BaseFragment
import kotlinx.android.synthetic.main.actionbar_toolbar.*
import kotlinx.android.synthetic.main.fragment_character_details.*
import kotlinx.android.synthetic.main.item_character_search.*
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
        }

    }

    override fun hideLoading() {
        srlDetails.isRefreshing = false
    }

    override fun showLoading() {
        srlDetails.isRefreshing = true
    }


}