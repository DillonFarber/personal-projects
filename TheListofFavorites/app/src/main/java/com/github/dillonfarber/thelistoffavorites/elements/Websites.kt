package com.github.dillonfarber.thelistoffavorites.elements

import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB.Websites_IGDB
import com.github.dillonfarber.thelistoffavorites.R
import com.github.dillonfarber.thelistoffavorites.WebsiteViewActivity


enum class Companies(id: Int){
    Official(id = 1){ override fun Name(): String{return "Officail"} },
    WIKIA(id = 2){ override fun Name():String{return "Wikia"}},
    WIKIPEDIA(id = 3){ override fun Name(): String {return "Wikipedia"}},
    FACEBOOK(id = 4){override fun Name(): String {return "FaceBook"}},
    TWITTER(id = 5){ override fun Name(): String {return "Twitter"}},
    TWITCH(id = 6){ override fun Name(): String {return "Twitch"}},
    INSTAGRAM(id = 8){ override fun Name(): String {return "Instagram"}},
    YOUTUBE(id = 9){ override fun Name(): String {return "YoutTube"}},
    IPHONE(id = 10){ override fun Name(): String {return "IPhone"}},
    IPAD(id = 11){ override fun Name(): String {return "IPad"}},
    ANDROID(id = 12){ override fun Name(): String {return "Android"}},
    STEAM(id = 13){ override fun Name(): String {return "Steam"}},
    REDDIT(id = 14){ override fun Name(): String {return "Reddit"}},
    ITCH(id = 15){ override fun Name(): String {return "itch.io"}},
    EPICGAMES(id = 16){ override fun Name(): String {return "Epic Games"}},
    GOG(id = 17){ override fun Name(): String {return "GOG"}},
    DISCORD(id = 18){ override fun Name(): String {return "Discord"}};

    abstract fun Name(): String
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebsiteView(
    weburl: String,
    webName: String,
    viewActivity: ComponentActivity

){
    var webView: WebView? = null
    var backAndForwardEnable by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.End) {
                        Text(text = webName)
                    }
                },
                navigationIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically)
                    {
                        Column() {
                            IconButton(onClick = {

                                viewActivity.finish()
                            }) {
                                Icon(Icons.Filled.StopCircle, "close window")
                            }
                        }
                        Column() {
                            IconButton(onClick = {
                                webView?.goBack()
                            }) {
                                Icon(Icons.Filled.ArrowBack, "goBack")
                            }
                        }
                        Column() {
                            IconButton(onClick = {
                                webView?.goForward()
                            }) {
                                Icon(Icons.Filled.ArrowForward, "goFoward")
                            }
                        }
                    }
                }
            )
        },
        content = { it ->
            it.apply {
                PaddingValues(0.dp)
                AndroidView(factory = {
                    WebView(it).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        webViewClient = object : WebViewClient() {
                            override fun onPageStarted(
                                view: WebView,
                                url: String?,
                                favicon: Bitmap?,

                            ) {
                                backAndForwardEnable = view.canGoBackOrForward(2)
                            }
                        }
                        settings.javaScriptEnabled = true
                        loadUrl(weburl)
                        webView = this
                    }
                },
                    update = {
                        webView = it
                    }
                )
                BackHandler(enabled = backAndForwardEnable) {
                    webView?.goBack()
                }
            }

        }
    )
}

@Composable
fun WebsiteIconGrid(websites: List<Websites_IGDB>, viewActivity: ComponentActivity){
    val websMap = mutableMapOf<String,String>()
    if(websites.isNotEmpty()) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            content = {
                items(websites) {
                    Log.i("LazyGrid", it.toString())
                    Log.i("LazyGridVA", viewActivity.toString())
                    WebsiteCard(it, viewActivity)
                }
            },
            modifier = Modifier
                .scrollable(state = rememberScrollState(), orientation = Orientation.Horizontal)
                .offset(0.dp, (-30).dp)
                .height(45.dp)
                .padding(start = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),

            )
    }
}

@Composable
fun WebsiteCard(website: Websites_IGDB, viewActivity: ComponentActivity){
    var websiteCompanyNameAndImage = GetWebName(website.category!!)
    Row(
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(0.dp)
        ) {
            Log.i("Image Resource", websiteCompanyNameAndImage["ResourceID"].toString())
            if(websiteCompanyNameAndImage["ResourceID"] != null) {
                Image(
                    painter = painterResource(id = websiteCompanyNameAndImage["ResourceID"]!! as Int),
                    contentDescription = websiteCompanyNameAndImage["Name"].toString(),
                    modifier = Modifier

                        .clip(RoundedCornerShape(25.dp))
                        .size(35.dp)
                        .clickable {
                            val intent = Intent(viewActivity, WebsiteViewActivity::class.java)
                            intent.putExtra("url", website.url)
                            intent.putExtra(
                                "name",
                                GetWebName(website.category!!)["Name"].toString()
                            )
                            viewActivity.startActivity(intent)
                        }
                        .background(color = Color.DarkGray)
                )
            }
            else {
                Image(
                    painter = painterResource(id = R.drawable.unknown),
                    contentDescription = websiteCompanyNameAndImage["Name"].toString(),
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(viewActivity, WebsiteViewActivity::class.java)
                            intent.putExtra("url", website.url)
                            intent.putExtra(
                                "name",
                                GetWebName(website.category!!)["Name"].toString()
                            )
                            viewActivity.startActivity(intent)
                        }
                )
            }
        }
    }
}

fun GetWebName(id: Int): MutableMap<String, Any?> {
    val nameAndImageCompanyMap = mutableMapOf<String, Any?>()
    when(id){
        1 -> {
            nameAndImageCompanyMap["Name"] = Companies.Official.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.home_button
            return nameAndImageCompanyMap
        }
        2 -> {
            nameAndImageCompanyMap["Name"] = Companies.WIKIA.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.social
            return nameAndImageCompanyMap
        }
        3 -> {
            nameAndImageCompanyMap["Name"] = Companies.WIKIPEDIA.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.social_2
            return nameAndImageCompanyMap}
        4 -> {
            nameAndImageCompanyMap["Name"] = Companies.FACEBOOK.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.facebook
            return nameAndImageCompanyMap
        }
        5 -> {
            nameAndImageCompanyMap["Name"] = Companies.TWITTER.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.twitter_96
            return nameAndImageCompanyMap
        }
        6 -> {
            nameAndImageCompanyMap["Name"] = Companies.TWITCH.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.twitch
            return nameAndImageCompanyMap
        }
        8 -> {
            nameAndImageCompanyMap["Name"] = Companies.INSTAGRAM.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.instagram_48
            return nameAndImageCompanyMap
        }
        9 -> {
            nameAndImageCompanyMap["Name"] = Companies.YOUTUBE.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.youtube
            return nameAndImageCompanyMap
        }
        10 -> {
            nameAndImageCompanyMap["Name"] = Companies.IPHONE.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.apple_2
            return nameAndImageCompanyMap
        }
        11 -> {
            nameAndImageCompanyMap["Name"] = Companies.IPAD.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.ipad
            return nameAndImageCompanyMap
        }
        12 -> {
            nameAndImageCompanyMap["Name"] = Companies.ANDROID.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.android
            return nameAndImageCompanyMap
        }
        13 -> {
            nameAndImageCompanyMap["Name"] = Companies.STEAM.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.steam_96
            return nameAndImageCompanyMap
        }
        14 -> {
            nameAndImageCompanyMap["Name"] = Companies.REDDIT.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.reddit
            return nameAndImageCompanyMap
        }
        15 -> {
            nameAndImageCompanyMap["Name"] = Companies.ITCH.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.itch_io_original
            return nameAndImageCompanyMap
        }
        16 -> {
            nameAndImageCompanyMap["Name"] = Companies.EPICGAMES.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.epic_games_96
            return nameAndImageCompanyMap
        }
        17 -> {
            nameAndImageCompanyMap["Name"] = Companies.GOG.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.gog_50
            return nameAndImageCompanyMap
        }
        18 -> {
            nameAndImageCompanyMap["Name"] = Companies.DISCORD.Name()
            nameAndImageCompanyMap["ResourceID"] = R.drawable.discord_2
            return nameAndImageCompanyMap
        }
        else -> {
            nameAndImageCompanyMap["Name"] = "Not Available"
            nameAndImageCompanyMap["ResourceID"] = R.drawable.unknown
            return nameAndImageCompanyMap
        }
    }
}