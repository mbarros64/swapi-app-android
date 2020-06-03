package com.mbarros64.swapi_app_android.characters.details.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.mbarros64.swapi_app_android.characters.R
import com.mbarros64.swapi_app_android.characters.details.model.CharacterDetailsModel
import kotlinx.android.synthetic.main.view_character_species_details.view.*

class SpecieDetailsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.view_character_species_details, this, true)
    }

    fun setSpecieAndLanguage(details: CharacterDetailsModel.SpeciesDetails) {
        tvSpeciesName.text = details.species
        tvSpeciesLanguage.text = String.format(context.getString(R.string.speak), details.language)
        tvHomeworld.text = String.format(context.getString(R.string.live_in), details.homeworld)
        tvPopulation.text = String.format(context.getString(R.string.population), details.population)
    }

}