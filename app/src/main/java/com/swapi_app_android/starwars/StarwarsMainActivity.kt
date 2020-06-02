package com.mbarros64.swapi_app_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.swapi_app_android.starwars.utils.Activities
import com.swapi_app_android.starwars.utils.intentTo

class StarwarsMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starwars_main)

        startActivity(intentTo(Activities.Characters))
        finish()
    }
}
