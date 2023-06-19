package com.github.dillonfarber.thelistoffavorites

import android.util.Log
import okhttp3.*
import okhttp3.internal.http2.Header
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

/**
 * IGDBService class is created to deal with API calls to the IGDB API, Controlling the
 * information for the application. Created to be a singleton class, using a double locking
 * of the class for no overlap in calls to API.
 * **/
class IGDBService private constructor() {
    companion object{
        @Volatile
        private var instance: IGDBService? = null
        fun getInstance(): IGDBService {
            if(instance == null){
                 synchronized(this){
                     if(instance == null)
                         instance = IGDBService()
                 }
            }
            return instance!!
        }
    }


    private val client: OkHttpClient = OkHttpClient()
    private var bearerTokens: JSONArray? = null
    private val IGDBurl: String = "https://api.igdb.com/v4"
    private val twitterURL: String = "https://id.twitch.tv/oauth2/token"
    private val body: FormBody = FormBody.Builder()
        .add("client_id" , "wdcga06wfwk5r6ioi0el9i89sstg6m")
        .add("client_secret" ,"ga6xcepjh9tuucl4kjkwfof9he0bx6")
        .add("grant_type", "client_credentials")
        .build()
    private val requestHeader: Headers = Headers.Builder()
        .add("client_id" , "wdcga06wfwk5r6ioi0el9i89sstg6m")
        .add("client_secret" ,"ga6xcepjh9tuucl4kjkwfof9he0bx6")
        .add("grant_type", "client_credentials")
        .build()
    var bodyMap = mutableMapOf("fields age_ratings" to "",
        "aggregated_rating_count" to "", "alternative_names" to "", "artworks" to "",
        "bundles" to "", "category" to "", "checksum" to "", "collection" to "", "cover" to "",
        "created_at" to "", "dlcs" to "", "expanded_games" to "", "expansions" to "",
        "external_games" to "", "first_release_date" to "", "follows" to "", "forks" to "",
        "franchise" to "", "franchises" to "", "game_engines" to "", "game_localizations" to "",
        "game_modes" to "", "genres" to "", "hypes" to "", "involved_companies" to "",
        "keywords" to "", "language_supports" to "", "multiplayer_modes" to "", "name" to "",
        "parent_game" to "", "platforms" to "", "player_perspectives" to "", "ports" to "",
        "rating" to "", "rating_count" to "", "release_dates" to "", "remakes" to "",
        "remasters" to "", "screenshots" to "", "similar_games" to "", "slug" to "",
        "standalone_expansions" to "", "status" to "", "storyline" to "", "summary" to "",
        "tags" to "", "themes" to "", "total_rating" to "", "total_rating_count" to "",
        "updated_at" to "", "url" to "", "version_parent" to "", "version_title" to "",
        "videos" to "", "websites" to "")

    private var bearerTokenFlag: Boolean = true

    private fun BearerToken() {
        val request = Request.Builder()
            .url(twitterURL)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    bearerTokens = response.body()!!.toString().let {JSONArray(it)}
                    Log.i("Task",response.body()!!.string())
                }
            }
        })

    }
    fun getBearerToken(): JSONArray? {
        return this.bearerTokens
    }

    init {
        BearerToken()
        if(bearerTokens != null){
            this.bearerTokenFlag = false
        }
        Log.i("Task", bearerTokens.toString())

    }

    fun gamesLookup(bodyMap: MutableMap<String, String?>, parameters: String?, ): JSONArray? {
        val url = this.IGDBurl + "/games"
        var builder = FormBody.Builder()
        var games: JSONArray? = null
        for((key, value) in bodyMap){
            if(value != ""){
                builder.add(key, value!!)
            }
        }
        builder.add("where", parameters)

        val requestBody = builder.build()
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .post(b)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    games = JSONArray(response.body()!!)
                    Log.i("Task", response.body()!!.string())
                }
            }
        })
        return games
    }
}