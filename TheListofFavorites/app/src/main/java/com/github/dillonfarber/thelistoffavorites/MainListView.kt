package com.github.dillonfarber.thelistoffavorites


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.dillonfarber.thelistoffavorites.mediaTypes.Anime
import com.github.dillonfarber.thelistoffavorites.mediaTypes.AnimeAdapter
import com.github.dillonfarber.thelistoffavorites.mediaTypes.Game
import com.github.dillonfarber.thelistoffavorites.mediaTypes.GameAdapter
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import kotlin.collections.ArrayList


class MainListView : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list_view)

        val popGames: RecyclerView = findViewById(R.id.PopularGames)
        val popBooks: RecyclerView = findViewById(R.id.PopularBooks)
        val popMovies: RecyclerView = findViewById(R.id.PopularMovies)
        val popTVShows: RecyclerView = findViewById(R.id.PopularTVShows)
        val popAnime: RecyclerView = findViewById(R.id.PopularAnime)

//        val igdbService: IGDBService = IGDBService.getInstance()
//        Toast.makeText(this@MainListView, igdbService.getBearerToken(), Toast.LENGTH_SHORT).show()
        val client: OkHttpClient = OkHttpClient()
        var body = FormBody.Builder()
            .add("client_id", "wdcga06wfwk5r6ioi0el9i89sstg6m")
            .add("client_secret","ga6xcepjh9tuucl4kjkwfof9he0bx6")
            .add("grant_type", "client_credentials")
            .build()

        val request = Request.Builder()
            .method("POST", body)
            .url("https://id.twitch.tv/oauth2/token")
            .build()

        val headers = Headers.Builder()
            .add("X-RapidAPI-Key", "b24debd91cmsh432705d3770c151p14f0b3jsn7190aa2798b6")
            .add("X-RapidAPI-Host", "rawg-video-games-database.p.rapidapi.com")
            .build()
        val request1 = Request.Builder()
            .url("https://rawg-video-games-database.p.rapidapi.com/games/%7Bgame_pk%7D")
            .headers(headers)
            .build()

//        val responseString = try{
//            val response = client.newCall(request).execute()
//           response.body()?.string()
//        } catch (error: java.lang.Exception){
//            null
//        }


        val response = client.newCall(request).enqueue()

//        Toast.makeText(this@MainListView, responseString, Toast.LENGTH_SHORT).show()

        val game: Game = Game("Ghost of Tsushima",
            "RPG",
            50.0,
            "September, 9, 2020",
            "Sucker Punch Productions",
            "Sony Interactive Entertainment",
            R.drawable.ghost_of_tsushima_logo_4k_p1_240x320)

        val gameModelArrayList: ArrayList<Game> = ArrayList()
        for (i in 0..10)
            gameModelArrayList.add(game)
        val gameAdapter = GameAdapter(this, gameModelArrayList)
        val gameLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        popGames.layoutManager = gameLayoutManager
        popGames.adapter = gameAdapter

        val anime: Anime = Anime("Tokyo Ghoul",
            "Dark Fantasy, Horror",
            24.0,
            "setp, 20, 2012",
            "Studio Bones",
            "Viz Media",
            R.drawable.tokyo_ghoul)

        val animeModelArray: ArrayList<Anime> = ArrayList()
        for(i in 0..10)
            animeModelArray.add(anime)
        val animeAdapter: AnimeAdapter = AnimeAdapter(this, animeModelArray)
        val animeLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        popAnime.layoutManager = animeLayoutManager
        popAnime.adapter = animeAdapter



    }
}