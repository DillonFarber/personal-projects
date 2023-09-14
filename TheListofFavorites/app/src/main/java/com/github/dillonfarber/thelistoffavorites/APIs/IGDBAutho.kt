package com.github.dillonfarber.thelistoffavorites.APIs

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.dillonfarber.thelistoffavorites.BuildConfig
import com.github.dillonfarber.thelistoffavorites.DataClasses.Request.BearerTokenResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json


object IGDBAutho: ViewModel() {


    var bearerTokenFlag = false
    var token = BearerTokenResponse()

    val bearerToken: MutableLiveData<BearerTokenResponse> by lazy {
        MutableLiveData<BearerTokenResponse>()
    }

    fun getBearerToken() {
        viewModelScope.launch(Dispatchers.IO) {
            val client = HttpClient(CIO) {
                install(HttpTimeout){
                    requestTimeoutMillis = 15000
                }
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                    })
                }
            }
            try {
                val response = client
                    .post("https://id.twitch.tv/oauth2/token?client_id=" +
                            "${BuildConfig.IGDB_CLIENT_ID}&" +
                            "client_secret=${BuildConfig.IGDB_CLIENT_SECRET}&" +
                            "grant_type=client_credentials")
                    {
                    contentType(ContentType.Application.Json)
                }
                val list = fromJson<BearerTokenResponse>(response.body<String>())
                bearerToken.postValue(list)
                client.close()
            } catch (e: HttpRequestTimeoutException){
                Log.i("timeout", e.toString())
            }
            client.close()
        }
    }
    inline fun <reified T> fromJson(json: String?): T {
        return Gson().fromJson<T>(json, object: TypeToken<T>(){}.type)
    }
}