package com.github.dillonfarber.thelistoffavorites.APIs

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.dillonfarber.thelistoffavorites.BuildConfig
import com.github.dillonfarber.thelistoffavorites.DataClasses.TMDB.MovieDetails
import com.github.dillonfarber.thelistoffavorites.DataClasses.TMDB.TMDB_Configuration
import com.github.dillonfarber.thelistoffavorites.DataClasses.TMDB.TMDB_MovieResults
import com.github.dillonfarber.thelistoffavorites.DataClasses.TMDB.TMDB_ResponseMovie
import com.github.dillonfarber.thelistoffavorites.DataClasses.TMDB.TMDB_ResponseTV
import com.github.dillonfarber.thelistoffavorites.DataClasses.TMDB.TMDB_TVResults
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

object TMDB_API: ViewModel() {

    val tm_movies: MutableLiveData<ArrayList<TMDB_MovieResults>>? by lazy {
        MutableLiveData<ArrayList<TMDB_MovieResults>>()
    }
    private val popularMoviesList: ArrayList<TMDB_MovieResults> = arrayListOf()
    private val currentPopMoviePage: Int = 1

    val tm_tv: MutableLiveData<ArrayList<TMDB_TVResults>>? by lazy {
        MutableLiveData<ArrayList<TMDB_TVResults>>()
    }
    private val popularTvList: ArrayList<TMDB_TVResults> = arrayListOf()
    private val currentPopTVPage: Int = 1

    val tm_movieDetails: MutableLiveData<MovieDetails>? by lazy {
        MutableLiveData<MovieDetails>()
    }

    private val popularAnimeTv: ArrayList<TMDB_TVResults> = arrayListOf()
    val tm_animeTv: MutableLiveData<ArrayList<TMDB_TVResults>>? by lazy {
        MutableLiveData<ArrayList<TMDB_TVResults>>()
    }
    private val popularAnimeMovie: ArrayList<TMDB_MovieResults> = arrayListOf()
    val tm_animeMovie: MutableLiveData<ArrayList<TMDB_MovieResults>>? by lazy {
        MutableLiveData<ArrayList<TMDB_MovieResults>>()
    }

    val tm_configuration: MutableLiveData<TMDB_Configuration>? by lazy {
        MutableLiveData<TMDB_Configuration>()
    }


    fun getMovies(page: Int = 1) {
        viewModelScope.async(Dispatchers.Default) {
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
                "https://api.themoviedb.org/3/movie/popular?" +
                        "language=en-US&page=$page" +
                        "&sort_by=popularity.desc"
            ) {

                url {
                    headers {
                        append("Accept", "application/json")
                        append("Authorization", "Bearer ${BuildConfig.TMDB_READ_ACCESS_KEY}")
                    }
                }
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            if (response.status.value == 200) {
                popularMoviesList.addAll(fromJson<TMDB_ResponseMovie>(response.body<String>()).results)
                tm_movies?.postValue(popularMoviesList)
            }
            client.close()
        }
    }

    fun getAnime(page: Int = 1){
        viewModelScope.async(Dispatchers.Default) {
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
//            val response = client.get(
//                "https://api.themoviedb.org/3/discover/movie" +
//                        "?include_adult=false&include_null_first_air_dates=false" +
//                        "&language=en-US&page=$page&sort_by=popularity.desc&with_keywords=210024"
//            ) {
//
//                url {
//                    headers {
//                        append("Accept", "application/json")
//                        append("Authorization", "Bearer ${BuildConfig.TMDB_READ_ACCESS_KEY}")
//                    }
//                }
//                contentType(ContentType.Application.Json)
//                setBody(body)
//            }
            val responseTv = client.get(
                "https://api.themoviedb.org/3/discover/tv?" +
                        "include_adult=false&include_null_first_air_dates=false" +
                        "&language=en-US&page=$page&sort_by=popularity.desc&with_keywords=210024"
            ) {

                url {
                    headers {
                        append("Accept", "application/json")
                        append("Authorization", "Bearer ${BuildConfig.TMDB_READ_ACCESS_KEY}")
                    }
                }
                contentType(ContentType.Application.Json)
                setBody(body)
            }

//            if (response.status.value == 200 && responseTv.status.value == 200) {
//                popularAnimeMovie.addAll(fromJson<TMDB_ResponseMovie>(response.body<String>()).results)
//                tm_animeMovie?.postValue(popularAnimeMovie)
//            }
            if (responseTv.status.value == 200){
                popularAnimeTv.addAll(fromJson<TMDB_ResponseTV>(responseTv.body<String>()).results)
                tm_animeTv?.postValue(popularAnimeTv)
            }
            client.close()
        }
    }

    fun getMovieDetails(id: Int) {
        viewModelScope.async(Dispatchers.Default) {
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
                "https://api.themoviedb.org/3/movie/$id?" +
                        "language=en-US&append_to_response=videos,credits,keywords,similar"
            ) {

                url {
                    headers {
                        append("Accept", "application/json")
                        append("Authorization", "Bearer ${BuildConfig.TMDB_READ_ACCESS_KEY}")
                    }
                }
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            Log.i("MovieDetailsStatus", response.status.value.toString())
            if (response.status.value == 200) {
                val movie = fromJson<MovieDetails>(response.body<String>())
                tm_movieDetails?.postValue(movie)
                Log.i("MovieDetails", movie.toString())
            }
            client.close()
        }
    }

    fun getTVShows(page: Int = 1) {
        viewModelScope.async(Dispatchers.Default) {
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
                "https://api.themoviedb.org/3/tv/popular?" +
                        "include_adult=false" +
                        "&include_video=false&language=en-US&page=$page" +
                        "&sort_by=popularity.desc"
            ) {

                url {
                    headers {
                        append("Accept", "application/json")
                        append("Authorization", "Bearer ${BuildConfig.TMDB_READ_ACCESS_KEY}")
                    }
                }
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            if (response.status.value == 200) {
                popularTvList.addAll(fromJson<TMDB_ResponseTV>(response.body<String>()).results)
                tm_tv?.postValue(popularTvList)
            }
            client.close()
        }
    }

    fun getConfiguration(){
        viewModelScope.async(Dispatchers.Default) {
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
                "https://api.themoviedb.org/3/configuration"
            ) {

                url {
                    headers {
                        append("Accept", "application/json")
                        append("Authorization", "Bearer ${BuildConfig.TMDB_READ_ACCESS_KEY}")
                    }
                }
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            if (response.status.value == 200) {
                val config = fromJson<TMDB_Configuration>(response.body<String>())
                tm_configuration?.postValue(config)
            }
            client.close()
        }
    }

    inline fun <reified T> fromJson(json: String?): T {
        return Gson().fromJson<T>(json, object : TypeToken<T>() {}.type)
    }
}