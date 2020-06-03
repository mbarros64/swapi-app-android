package com.mbarros64.swapi_app_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mbarros64.swapi_app_android.extensions.Activities
import com.mbarros64.swapi_app_android.extensions.intentTo

class StarwarsMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starwars_main)

        startActivity(intentTo(Activities.Characters))
        finish()
    }
}
