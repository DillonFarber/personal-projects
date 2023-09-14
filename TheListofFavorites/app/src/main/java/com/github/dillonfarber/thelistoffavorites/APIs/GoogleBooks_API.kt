package com.github.dillonfarber.thelistoffavorites.APIs

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.dillonfarber.thelistoffavorites.BuildConfig
import com.github.dillonfarber.thelistoffavorites.DataClasses.GoogleBooks.GoogleBooksResponse
import com.github.dillonfarber.thelistoffavorites.DataClasses.GoogleBooks.GoogleBooksResults
import com.github.dillonfarber.thelistoffavorites.DataClasses.TMDB.TMDB_ResponseMovie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.serialization.json.Json

object GoogleBooks_API: ViewModel() {

    val ggl_books: MutableLiveData<ArrayList<GoogleBooksResults>>? by lazy {
        MutableLiveData<ArrayList<GoogleBooksResults>>()
    }

    fun getBooks(startIndex: Int = 0, query: String = "", langRestrict: String = "en") {
        viewModelScope.async(Dispatchers.Default) {
            var query: String = query
            if(query == "")
                query += " +subject:fiction"
            val client = HttpClient(CIO) {
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                    })
                }
                install(HttpRequestRetry) {
                    maxRetries = 0
                }
            }
            val response = client.get(
                "https://www.googleapis.com/books/v1/volumes"
            ) {

                url {
                    headers {
                        append("Accept", "application/json")
                    }
                    parameters {
                        parameter("q", query)
                        parameter("printType", "books")
                        parameter("orderBy", "newest")
                        parameter("maxResults", "15")
                        parameter("langRestrict", langRestrict)
                        parameter("key", BuildConfig.GOOGLE_ACCESS_KEY)
                    }
                }
                contentType(ContentType.Application.Json)
            }
            if (response.status.value == 200) {
                ggl_books?.postValue(fromJson<GoogleBooksResponse>(response.body<String>()).items)
            }
            client.close()
        }
    }


    inline fun <reified T> fromJson(json: String?): T {
        return Gson().fromJson<T>(json, object: TypeToken<T>(){}.type)
    }
}