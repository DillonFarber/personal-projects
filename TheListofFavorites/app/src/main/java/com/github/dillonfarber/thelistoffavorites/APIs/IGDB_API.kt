package com.github.dillonfarber.thelistoffavorites.APIs

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.dillonfarber.thelistoffavorites.BuildConfig
import com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB.AgeRatings_IGDB
import com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB.Companies_IGDB
import com.github.dillonfarber.thelistoffavorites.DataClasses.Request.BearerTokenResponse
import com.github.dillonfarber.thelistoffavorites.DataClasses.Request.IGDBGamesResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.serialization.json.*


object IGDB_API : ViewModel() {
    private lateinit var bearerToken: String
    private var expires: Int = 0
    val igGames: MutableLiveData<ArrayList<IGDBGamesResponse>>? by lazy {
        MutableLiveData<ArrayList<IGDBGamesResponse>>()
    }
    val igRatings: MutableLiveData<ArrayList<AgeRatings_IGDB>>? by lazy {
        MutableLiveData<ArrayList<AgeRatings_IGDB>>()
    }
    val igCompanies: MutableLiveData<ArrayList<Companies_IGDB>>? by lazy {
        MutableLiveData<ArrayList<Companies_IGDB>>()
    }
    private val similarGames: MutableLiveData<List<IGDBGamesResponse>>? by lazy {
        MutableLiveData<List<IGDBGamesResponse>>(null)
    }
    var igSimilarGames: LiveData<List<IGDBGamesResponse>>? = similarGames

    val gson = GsonBuilder().create()

    fun getGames(bearerToken: BearerTokenResponse) {
        val body = "fields name, cover.image_id, age_ratings.category, age_ratings.content_descriptions.description," +
                "genres.name, platforms.name, artworks.image_id, age_ratings.rating," +
                "summary, storyline, involved_companies.developer, involved_companies.porting, " +
                "involved_companies.publisher, involved_companies.supporting," +
                " involved_companies.company.name, player_perspectives.name, game_modes.name," +
                "involved_companies.company.url, screenshots.image_id,videos.video_id, videos.name, " +
                "rating, rating_count, keywords.name, url, websites.category, websites.url, " +
                "similar_games.id; where rating > 70 & platforms.category = (1, 6) " +
                "&first_release_date > 1659934353; sort rating desc; limit 20;"

        viewModelScope.async(Dispatchers.Default) {
            val client = HttpClient(CIO){
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                    })
                }
                install(HttpRequestRetry){
                    maxRetries = 0
                }
            }
            val response = client.post("https://api.igdb.com/v4/games") {

                url {
                    headers{
                        append("Client-ID", BuildConfig.IGDB_CLIENT_ID)
                        append("Authorization", "Bearer ${bearerToken.accessToken}")
                        append("Accept", "application/json")
                    }
                }
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            if(response.status.value == 200) {
                val list = fromJson<ArrayList<IGDBGamesResponse>>(response.body<String>())
                igGames?.postValue(list)
            }
            client.close()
        }


    }

    fun getRatings(bearerToken: BearerTokenResponse){
        viewModelScope.launch(Dispatchers.IO) {
            val client = HttpClient(CIO){
                install(ContentNegotiation){
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                    })
                }
            }
            val response = client.post("https://api.igdb.com/v4/age_ratings"){
                url{
                    headers{
                        append("Client-ID", "wdcga06wfwk5r6ioi0el9i89sstg6m")
                        append("Authorization", "Bearer ${bearerToken.accessToken}")
                        append("Accept", "application/xml")
                    }
                }
                contentType(ContentType.Application.Json)
                setBody("fields *;")
            }
            val list = fromJson<ArrayList<AgeRatings_IGDB>>(response.body<String>())
            igRatings?.postValue(list)
        }
    }

    fun getInvolvedCompanies(bearerToken: BearerTokenResponse, companyID: Int? = null, listOfIDs: List<Int>? = null){
        var list: ArrayList<Companies_IGDB>
        viewModelScope.launch(Dispatchers.IO) {
            val client = HttpClient(CIO){
                install(ContentNegotiation){
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                    })
                }
            }
            val response = client.post("https://api.igdb.com/v4/companies"){
                url{
                    headers{
                        append("Client-ID", BuildConfig.IGDB_CLIENT_ID)
                        append("Authorization", "Bearer ${bearerToken.accessToken.toString()}")
                        append("Accept", "application/json")
                    }
                }
                contentType(ContentType.Application.Json)
                setBody("fields name, published, url, websites; where id = " +
                        "${listOfIDs?.joinToString(separator = ",", prefix = "(", postfix = ")")};")

            }
            Log.i("response", response.status.toString())
            if(response.status.value == 200) {
                if (companyID != null) {
                    val data = fromJson<Companies_IGDB>(response.body<String>())
                    list = arrayListOf()
                    list.add(data)

                } else {
                    if (listOfIDs?.size!! <= 1) {
                        val data = fromJson<Companies_IGDB>(response.body<String>())
                        list = arrayListOf()
                        list.add(data)
                    } else
                        list = fromJson(response.body<String>())
                }
                igCompanies?.postValue(list)
            }
            client.close()
        }
    }

    fun getSimilarGames(bearerToken: BearerTokenResponse, ids: ArrayList<String>) {

        val body = "fields name, cover.image_id, age_ratings.category, age_ratings.content_descriptions.description," +
                "genres.name, platforms.name, artworks.image_id, age_ratings.rating," +
                "summary, storyline, involved_companies.developer, involved_companies.porting," +
                "involved_companies.publisher, involved_companies.supporting," +
                "involved_companies.company.name, player_perspectives.name, game_modes.name," +
                "involved_companies.company.url, screenshots.image_id,videos.video_id, videos.name," +
                "rating, rating_count, keywords.name, url, websites.category, websites.url," +
                "similar_games.id; where id = (${ids.joinToString(separator = ",")});"

        viewModelScope.launch(Dispatchers.IO) {
            val client = HttpClient(CIO){
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                    })
                }
            }
            val response = client.post("https://api.igdb.com/v4/games") {

                url {
                    headers{
                        append("Client-ID", BuildConfig.IGDB_CLIENT_ID)
                        append("Authorization", "Bearer ${bearerToken.accessToken}")
                        append("Accept", "application/json")
                    }
                }
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            if(response.status.value == 200) {
                val list = fromJson<ArrayList<IGDBGamesResponse>>(response.body<String>())
                similarGames?.postValue(list)
            }
            client.close()
        }


    }

    inline fun <reified T> fromJson(json: String?): T {
        return Gson().fromJson<T>(json, object: TypeToken<T>(){}.type)
    }
}