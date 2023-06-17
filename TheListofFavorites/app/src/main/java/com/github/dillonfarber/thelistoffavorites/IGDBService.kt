package com.github.dillonfarber.thelistoffavorites

import android.util.Log
import okhttp3.*
import okhttp3.internal.http2.Header
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
    private var bearerTokens: JSONObject?
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

    private var bearerTokenFlag: Boolean = true

    private fun BearerToken(): JSONObject? {
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
                    bearerTokens = response.body()!!.toString().let{ JSONObject(it) }
                    Log.i("Task",response.body()!!.string())
                }
            }
        })
        return
    }
    fun getBearerToken(): String?{
        return this.bearerTokens.toString()
    }

    init {
        bearerTokens = BearerToken()
        if(bearerTokens != null){
            this.bearerTokenFlag = false
        }
        print(bearerTokens.toString())

    }





//    val (request, response, result) = "https://api.igdb.com/v4/games".httpPost()
//        .header("Client-ID" to "Client ID", "Authorization" to "Bearer access_token", "Accept" to "application/json")
//        .body("fields age_ratings,aggregated_rating,aggregated_rating_count,alternative_names,artworks," +
//                "bundles,category,checksum,collection,cover,created_at,dlcs,expanded_games,expansions,external_games," +
//                "first_release_date,follows,forks,franchise,franchises,game_engines,game_localizations,game_modes,genres,hypes," +
//                "involved_companies,keywords,language_supports,multiplayer_modes,name,parent_game,platforms,player_perspectives," +
//                "ports,rating,rating_count,release_dates,remakes,remasters,screenshots,similar_games,slug,standalone_expansions," +
//                "status,storyline,summary,tags,themes,total_rating,total_rating_count,updated_at,url,version_parent," +
//                "version_title,videos,websites;").responseString()

}