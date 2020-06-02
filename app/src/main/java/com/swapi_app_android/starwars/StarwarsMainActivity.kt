package com.swapi_app_android.starwars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mbarros64.swapi_app_android.R
import com.swapi_app_android.starwars.extensions.Activities
import com.swapi_app_android.starwars.extensions.intentTo

class StarwarsMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starwars_main)

        startActivity(intentTo(Activities.Characters))
        finish()
    }
}
