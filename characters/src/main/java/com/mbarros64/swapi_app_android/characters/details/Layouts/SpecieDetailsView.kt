package com.mbarros64.swapi_app_android.characters.details.Layouts

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.mbarros64.swapi_app_android.characters.R
import com.mbarros64.swapi_app_android.characters.details.models.SpeciesDetailsModel
import kotlinx.android.synthetic.main.view_character_species_details.view.*

class SpecieDetailsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.view_character_species_details, this, true)
    }

    fun specieDetails(detailsModel: SpeciesDetailsModel) {
        tvSpeciesName.text = detailsModel.species
        tvSpeciesLanguage.text = String.format(context.getString(R.string.speak), detailsModel.language)
        tvHomeworld.text = String.format(context.getString(R.string.live_in), detailsModel.homeworld)
        tvPopulation.text = String.format(context.getString(R.string.population), detailsModel.population)
    }

}