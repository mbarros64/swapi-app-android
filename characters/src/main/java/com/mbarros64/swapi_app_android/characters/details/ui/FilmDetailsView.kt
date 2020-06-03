package com.mbarros64.swapi_app_android.characters.details.ui

import android.content.Context
import android.icu.text.CaseMap
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.mbarros64.swapi_app_android.characters.R
import com.mbarros64.swapi_app_android.characters.details.model.OpeningCrawl
import com.mbarros64.swapi_app_android.characters.details.model.ReleaseDate
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

    fun filmDetails(details: Triple<CaseMap.Title, ReleaseDate, OpeningCrawl>) {
        tvFilmName.text = details.first
        tvFilmReleaseDate.text = String.format(context.getString(R.string.released_on), details.second)
        tvFilmCrawl.text = details.third
    }

}