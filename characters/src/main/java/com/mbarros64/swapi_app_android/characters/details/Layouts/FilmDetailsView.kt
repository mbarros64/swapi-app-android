package com.mbarros64.swapi_app_android.characters.details.Layouts

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.mbarros64.swapi_app_android.characters.R
import com.mbarros64.swapi_app_android.characters.details.models.CharacterDetailsModel
import kotlinx.android.synthetic.main.view_character_film_details.view.*

class FilmDetailsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.view_character_film_details, this, true)
    }

    fun filmDetails(details: CharacterDetailsModel.FilmDetailsModel) {
        tvFilmName.text = details.title
        tvFilmReleaseDate.text = String.format(context.getString(R.string.released_on), details.releaseDate)
        tvFilmCrawl.text = details.openingCrawl
    }

}