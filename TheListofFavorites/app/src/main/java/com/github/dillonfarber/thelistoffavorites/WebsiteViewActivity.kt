package com.github.dillonfarber.thelistoffavorites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.dillonfarber.thelistoffavorites.elements.WebsiteView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class WebsiteViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val weburl = intent.getStringExtra("url")
        val webName = intent.getStringExtra("name")

        setContent {
            WebsiteView(weburl = weburl!!, webName = webName!!, viewActivity = this)
        }
    }
    inline fun <reified T> fromJson(json: String?): T {
        return Gson().fromJson<T>(json, object : TypeToken<T>() {}.type)
    }
}


